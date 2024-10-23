package com.informatica.controle_material.domain.usecases.loan;

import java.util.Optional;

import com.informatica.controle_material.domain.model.Loan;

public interface FindLoanByIdUseCase {

  Optional<Loan> execute(Long id);

}
