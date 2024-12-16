package com.informatica.controle_material.data.usecase.loan;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.data.dto.equipment.EquipmentDTO;
import com.informatica.controle_material.data.dto.loan.LoanEquipmentDTO;
import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan.GetAllLoansWithEquipmentsUseCase;
import com.informatica.controle_material.infra.repository.LoanRepository;

@Service
public class GetAllLoansWithEquipmentsImpl implements GetAllLoansWithEquipmentsUseCase {

  @Autowired
  private LoanRepository loanRepository;

  @Override
  public List<LoanEquipmentDTO> execute() {
    List<Loan> loans = loanRepository.findAllLoansWithEquipments();

    List<LoanEquipmentDTO> loanEquipmentDTOs = loans.stream().map(loan -> new LoanEquipmentDTO(
        loan.getAmount(),
        loan.getDate(),
        loan.getReceiver().getRank() + " " + loan.getReceiver().getWarName(),
        loan.getMission(),
        loan.getEquipments().stream().map(equipment -> new EquipmentDTO(
            equipment.getName(),
            equipment.getAmount(),
            equipment.getSerialNumber(),
            equipment.getIsTemporary())).collect(Collectors.toList())))
        .collect(Collectors.toList());

    return loanEquipmentDTOs;
  }

}
