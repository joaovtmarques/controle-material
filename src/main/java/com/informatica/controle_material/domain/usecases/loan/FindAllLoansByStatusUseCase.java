package com.informatica.controle_material.domain.usecases.loan;

import java.util.List;

import com.informatica.controle_material.data.dto.loan.FindLoanByStatusDTO;
import com.informatica.controle_material.domain.model.Loan;

public interface FindAllLoansByStatusUseCase {

  List<Loan> execute(FindLoanByStatusDTO findLoanByStatusDTO);

}
