package com.informatica.controle_material.presentation.controller.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.loan.AddLoanDTO;
import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan.AddLoanUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/loans")
public class AddLoanController implements ControllerProtocol<AddLoanDTO, ResponseEntity<Loan>> {
  
  @Autowired
  private AddLoanUseCase addLoan;

  @Override
  @PostMapping
  public ResponseEntity<Loan> handle(@Valid @RequestBody AddLoanDTO addLoanDTO) {
    Loan loan = addLoan.execute(addLoanDTO);

    return ResponseEntity.status(201).body(loan);
  }

}