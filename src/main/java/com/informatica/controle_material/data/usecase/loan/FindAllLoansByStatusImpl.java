package com.informatica.controle_material.data.usecase.loan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.data.dto.loan.FindLoanByStatusDTO;
import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan.FindAllLoansByStatusUseCase;
import com.informatica.controle_material.infra.repository.LoanRepository;

@Service
public class FindAllLoansByStatusImpl implements FindAllLoansByStatusUseCase {
  
  @Autowired
  private LoanRepository loanRepository;

  @Override
  public List<Loan> execute(FindLoanByStatusDTO findLoanByStatusDTO) {
    return loanRepository.findAllByStatusAndType(findLoanByStatusDTO.status(),findLoanByStatusDTO.type());
  }

}
