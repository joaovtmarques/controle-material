package com.informatica.controle_material.domain.usecases.loan_doc;

import com.informatica.controle_material.domain.model.Loan;

public interface AddLoanDocUseCase {
 
  String execute(Loan loan);

}
