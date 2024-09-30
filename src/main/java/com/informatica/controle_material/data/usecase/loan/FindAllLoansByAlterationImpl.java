package com.informatica.controle_material.data.usecase.loan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan.FindAllLoansByAlterationUseCase;
import com.informatica.controle_material.infra.repository.LoanRepository;

@Service
public class FindAllLoansByAlterationImpl implements FindAllLoansByAlterationUseCase {
  
  @Autowired
  private LoanRepository loanRepository;

  @Override
  public List<Loan> execute(Boolean alteration, String type) {
    return loanRepository.findAllByAlterationAndType(alteration, type);
  }

}
