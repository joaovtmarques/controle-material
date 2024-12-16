package com.informatica.controle_material.domain.usecases.loan;

import java.util.List;

import com.informatica.controle_material.data.dto.loan.LoanEquipmentDTO;

public interface GetAllLoansWithEquipmentsUseCase {

  List<LoanEquipmentDTO> execute();

}
