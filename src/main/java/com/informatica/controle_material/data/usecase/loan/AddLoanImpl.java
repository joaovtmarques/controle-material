package com.informatica.controle_material.data.usecase.loan;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_material.data.dto.loan.AddLoanDTO;
import com.informatica.controle_material.data.exception.BadRequestException;
import com.informatica.controle_material.data.exception.NotFoundException;
import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.model.Receiver;
import com.informatica.controle_material.domain.model.User;
import com.informatica.controle_material.domain.usecases.loan.AddLoanUseCase;
import com.informatica.controle_material.domain.usecases.loan_doc.AddLoanDocUseCase;
import com.informatica.controle_material.infra.repository.EquipmentRepository;
import com.informatica.controle_material.infra.repository.ItemRepository;
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
  private AddLoanDocUseCase addLoanDoc;

  @Transactional
  @Override
  public Loan execute(AddLoanDTO addLoanDTO) {
    Optional<User> lenderExists = userRepository.findById(addLoanDTO.lenderId());
    
    if(!lenderExists.isPresent()) {
      throw new NotFoundException("O usuario "+addLoanDTO.lenderId()+" não existe");
    }
    
    Optional<Receiver> receiverExists = receiverRepository.findById(addLoanDTO.receiverId());
    
    if(!receiverExists.isPresent()) {
      throw new NotFoundException("O recebedor "+addLoanDTO.receiverId()+" não existe");
    }
    
    Loan loan = null;

    if(addLoanDTO.itemId() != null) {
      Optional<Item> itemExists = itemRepository.findById(addLoanDTO.itemId());
      if(!itemExists.isPresent()) {
        throw new NotFoundException("O item "+addLoanDTO.itemId()+" não existe");
      }
      Loan loanToSave = addLoanDTO.toModelWithItem(itemExists.get(), lenderExists.get(), receiverExists.get());
      loan = loanRepository.save(loanToSave);
      if(itemExists.get().getAmount() < loanToSave.getAmount()) {
        throw new BadRequestException("O item "+addLoanDTO.itemId()+" não possui a quantidade solicitada");
      }
      itemExists.get().setAmount(itemExists.get().getAmount() - loanToSave.getAmount());
      itemRepository.save(itemExists.get());
    }

    if(addLoanDTO.equipmentId() != null) {
      Optional<Equipment> equipmentExists = equipmentRepository.findById(addLoanDTO.equipmentId());
      if(!equipmentExists.isPresent()) {
        throw new NotFoundException("O equipamento "+addLoanDTO.equipmentId()+" não existe");
      }
      Loan loanToSave = addLoanDTO.toModelWithEquipment(equipmentExists.get(), lenderExists.get(), receiverExists.get());
      loan = loanRepository.save(loanToSave);
      if(equipmentExists.get().getAmount() < loanToSave.getAmount()) { 
        throw new BadRequestException("O equipamento "+addLoanDTO.equipmentId()+" não possui a quantidade solicitada");
      }
      equipmentExists.get().setAmount(equipmentExists.get().getAmount() - loanToSave.getAmount());
      equipmentExists.get().setState("CAUTELADO");
      equipmentRepository.save(equipmentExists.get());
    }

    addLoanDoc.execute(loan);

    return loan;
  }

}
