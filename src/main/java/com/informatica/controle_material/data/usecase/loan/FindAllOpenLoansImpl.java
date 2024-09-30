package com.informatica.controle_material.data.usecase.loan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan.FindAllOpenLoansUseCase;
import com.informatica.controle_material.infra.repository.LoanRepository;

@Service
public class FindAllOpenLoansImpl implements FindAllOpenLoansUseCase {
  
  @Autowired
  private LoanRepository loanRepository;

  @Override
  public List<Loan> execute(String type) {
    return loanRepository.findAllByStatusAndType("ABERTO", type);
  }

}
