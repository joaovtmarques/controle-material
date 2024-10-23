package com.informatica.controle_material.data.usecase.loan;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan.FindLoanByIdUseCase;
import com.informatica.controle_material.infra.repository.LoanRepository;

@Service
public class FindLoanByIdImpl implements FindLoanByIdUseCase {

  @Autowired
  private LoanRepository loanRepository;

  @Override
  public Optional<Loan> execute(Long id) {
    return loanRepository.findById(id);
  }

}
