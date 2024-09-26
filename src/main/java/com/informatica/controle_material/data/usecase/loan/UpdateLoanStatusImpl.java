package com.informatica.controle_material.data.usecase.loan;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_material.data.exception.NotFoundException;
import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan.UpdateLoanStatusUseCase;
import com.informatica.controle_material.infra.repository.LoanRepository;

@Service
public class UpdateLoanStatusImpl implements UpdateLoanStatusUseCase {
  
  @Autowired
  private LoanRepository loanRepository;

  @Transactional
  @Override
  public Loan execute(Long id, String status) {
    Optional<Loan> loanExists = loanRepository.findById(id);

    if(loanExists.isEmpty()) {
      throw new NotFoundException("A cautela "+id+" não existe");
    }

    loanExists.get().setStatus(status);
    return loanRepository.save(loanExists.get());
  }

}
