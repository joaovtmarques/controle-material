package com.informatica.controle_material.presentation.controller.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.loan.AddLoanDTO;
import com.informatica.controle_material.data.dto.loan.AddLoanResponseDTO;
import com.informatica.controle_material.domain.usecases.loan.AddLoanUseCase;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/loans")
public class AddLoanController {

  @Autowired
  private AddLoanUseCase addLoan;

  @PostMapping
  public ResponseEntity<AddLoanResponseDTO> handle(@Valid @RequestBody AddLoanDTO addLoanDTO) throws Exception {
    AddLoanResponseDTO loan = addLoan.execute(addLoanDTO);

    return ResponseEntity.status(201).body(loan);
  }

}
