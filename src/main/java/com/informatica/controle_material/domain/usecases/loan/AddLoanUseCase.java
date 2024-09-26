package com.informatica.controle_material.domain.usecases.loan;

import com.informatica.controle_material.data.dto.loan.AddLoanDTO;
import com.informatica.controle_material.data.dto.loan.AddLoanResponseDTO;

public interface AddLoanUseCase {
  
  AddLoanResponseDTO execute(AddLoanDTO addLoanDTO);

}
