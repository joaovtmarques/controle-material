package com.informatica.controle_material.domain.usecases.loan;

import java.util.List;

import com.informatica.controle_material.domain.model.Loan;

public interface FindAllLoansUseCase {
  
  List<Loan> execute(String type);

}
