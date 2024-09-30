package com.informatica.controle_material.domain.usecases.loan;

import java.util.List;

import com.informatica.controle_material.domain.model.Loan;

public interface FindAllLoansByAlterationUseCase {
  
  List<Loan> execute(Boolean alteration, String type);

}
