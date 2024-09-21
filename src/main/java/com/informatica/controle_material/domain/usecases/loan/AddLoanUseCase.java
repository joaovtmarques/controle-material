package com.informatica.controle_material.domain.usecases.loan;

import com.informatica.controle_material.data.dto.loan.AddLoanDTO;
import com.informatica.controle_material.domain.model.Loan;

public interface AddLoanUseCase {
  
  Loan execute(AddLoanDTO addLoanDTO);

}
