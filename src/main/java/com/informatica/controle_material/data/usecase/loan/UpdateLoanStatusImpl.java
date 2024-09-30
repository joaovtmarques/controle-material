package com.informatica.controle_material.data.usecase.loan;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_material.data.exception.NotFoundException;
import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan.UpdateLoanStatusUseCase;
import com.informatica.controle_material.infra.repository.EquipmentRepository;
import com.informatica.controle_material.infra.repository.ItemRepository;
import com.informatica.controle_material.infra.repository.LoanRepository;

@Service
public class UpdateLoanStatusImpl implements UpdateLoanStatusUseCase {
  
  @Autowired
  private LoanRepository loanRepository;

  @Autowired
  private EquipmentRepository equipmentRepository;

  @Autowired
  private ItemRepository itemRepository;

  @Transactional
  @Override
  public Loan execute(Long id, String status) {
    Optional<Loan> loanExists = loanRepository.findById(id);

    if(loanExists.isEmpty()) {
      throw new NotFoundException("A cautela "+id+" não existe");
    }

    if(loanExists.get().getEquipments() != null) {
      for(Equipment equipment : loanExists.get().getEquipments()) {
        equipment.setAmount(equipment.getAmount() + loanExists.get().getAmount());
        equipment.setAmountOut(equipment.getAmountOut() - loanExists.get().getAmount());
        equipmentRepository.save(equipment);
      }
    }

    if(loanExists.get().getItems() != null) {
      for(Item item : loanExists.get().getItems()) {
        item.setAmount(item.getAmount() + loanExists.get().getAmount());
        item.setAmountOut(item.getAmountOut() - loanExists.get().getAmount());
        itemRepository.save(item);
      }
    }

    loanExists.get().setStatus(status);
    return loanRepository.save(loanExists.get());
  }

}
