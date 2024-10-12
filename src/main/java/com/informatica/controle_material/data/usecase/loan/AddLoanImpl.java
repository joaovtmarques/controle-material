package com.informatica.controle_material.data.usecase.loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_material.data.dto.loan.AddLoanDTO;
import com.informatica.controle_material.data.dto.loan.AddLoanResponseDTO;
import com.informatica.controle_material.data.exception.BadRequestException;
import com.informatica.controle_material.data.exception.NotFoundException;
import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.model.LoanDoc;
import com.informatica.controle_material.domain.model.Receiver;
import com.informatica.controle_material.domain.model.User;
import com.informatica.controle_material.domain.usecases.loan.AddLoanUseCase;
import com.informatica.controle_material.domain.usecases.loan_doc.AddLoanDocUseCase;
import com.informatica.controle_material.domain.usecases.upload.UploadFileUseCase;
import com.informatica.controle_material.infra.factory.AddLoanDocFactory;
import com.informatica.controle_material.infra.repository.EquipmentRepository;
import com.informatica.controle_material.infra.repository.ItemRepository;
import com.informatica.controle_material.infra.repository.LoanDocRepository;
import com.informatica.controle_material.infra.repository.LoanRepository;
import com.informatica.controle_material.infra.repository.ReceiverRepository;
import com.informatica.controle_material.infra.repository.UserRepository;

@Service
public class AddLoanImpl implements AddLoanUseCase {

  @Autowired
  private LoanRepository loanRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ReceiverRepository receiverRepository;

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private EquipmentRepository equipmentRepository;

  @Autowired
  private LoanDocRepository loanDocRepository;

  @Autowired
  private UploadFileUseCase uploadFile;

  private final AddLoanDocFactory addLoanDocFactory;

  private AddLoanDocUseCase addLoanDoc;

  public AddLoanImpl(AddLoanDocFactory addLoanDocFactory) {
    this.addLoanDocFactory = addLoanDocFactory;
  }

  @Transactional
  @Override
  public AddLoanResponseDTO execute(AddLoanDTO addLoanDTO) throws Exception {
    Optional<User> lenderExists = userRepository.findById(addLoanDTO.lenderId());

    if (!lenderExists.isPresent()) {
      throw new NotFoundException("O usuario " + addLoanDTO.lenderId() + " não existe");
    }

    Optional<Receiver> receiverExists = receiverRepository.findById(addLoanDTO.receiverId());

    if (!receiverExists.isPresent()) {
      throw new NotFoundException("O recebedor " + addLoanDTO.receiverId() + " não existe");
    }

    Loan loan = null;

    if (addLoanDTO.itemsId() != null) {
      List<Item> items = new ArrayList<Item>();
      for (Long id : addLoanDTO.itemsId()) {
        Optional<Item> itemExists = itemRepository.findById(id);
        if (!itemExists.isPresent()) {
          throw new NotFoundException("O item " + id + " não existe");
        }
        if (itemExists.get().getAmount() < addLoanDTO.amount()) {
          throw new BadRequestException("O item " + itemExists.get().getId() + " não possui a quantidade solicitada");
        }
        itemExists.get().setAmount(itemExists.get().getAmount() - addLoanDTO.amount());
        itemExists.get().setAmountOut(itemExists.get().getAmountOut() + addLoanDTO.amount());
        items.add(itemExists.get());
      }
      Loan loanToSave = addLoanDTO.toModelWithItem(items, lenderExists.get(), receiverExists.get());
      loan = loanRepository.save(loanToSave);
      for (Item item : items) {
        itemRepository.save(item);
      }
    }

    if (addLoanDTO.equipmentsId() != null) {
      List<Equipment> equipments = new ArrayList<Equipment>();
      for (Long id : addLoanDTO.equipmentsId()) {
        Optional<Equipment> equipmentExists = equipmentRepository.findById(id);
        if (!equipmentExists.isPresent()) {
          throw new NotFoundException("O equipamento " + id + " não existe");
        }
        if (equipmentExists.get().getAmount() < addLoanDTO.amount()) {
          throw new BadRequestException(
              "O equipamento " + equipmentExists.get().getId() + " não possui a quantidade solicitada");
        }
        equipmentExists.get().setAmount(equipmentExists.get().getAmount() - addLoanDTO.amount());
        equipmentExists.get().setAmountOut(equipmentExists.get().getAmountOut() + addLoanDTO.amount());
        equipmentExists.get().setState("CAUTELADO");
        equipments.add(equipmentExists.get());
      }
      Loan loanToSave = addLoanDTO.toModelWithEquipment(equipments, lenderExists.get(), receiverExists.get());
      loan = loanRepository.save(loanToSave);
      for (Equipment equipment : equipments) {
        equipmentRepository.save(equipment);
      }
    }

    addLoanDoc = addLoanDocFactory.getAddLoanDoc(loan);

    String filePath = addLoanDoc.execute(loan);

    LoanDoc loanDoc = new LoanDoc();
    LoanDoc savedLoanDoc = loanDocRepository.save(loanDoc);
    loan.setLoanDoc(savedLoanDoc);
    Loan savedLoan = loanRepository.save(loan);

    savedLoanDoc.setLoan(savedLoan);

    File file = new File(filePath);
    String url = uploadFile.execute(file);
    savedLoan.getLoanDoc().setFilePath(url);
    savedLoanDoc.setFilePath(url);

    loanRepository.save(savedLoan);
    loanDocRepository.save(savedLoanDoc);

    return new AddLoanResponseDTO(savedLoan);
  }

}
