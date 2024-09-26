package com.informatica.controle_material.domain.usecases.loan;

import com.informatica.controle_material.domain.model.Loan;

public interface UpdateLoanStatusUseCase {
  
  public Loan execute(Long id, String status);

}
