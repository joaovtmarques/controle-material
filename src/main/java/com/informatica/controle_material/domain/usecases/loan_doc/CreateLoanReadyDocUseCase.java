package com.informatica.controle_material.domain.usecases.loan_doc;

import java.util.List;

import com.informatica.controle_material.data.dto.loan.LoanEquipmentDTO;
import com.informatica.controle_material.domain.model.Equipment;

public interface CreateLoanReadyDocUseCase {

  String execute(List<LoanEquipmentDTO> loans, List<Equipment> equipments);

}
