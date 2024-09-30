package com.informatica.controle_material.presentation.controller.loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.loan.UpdateLoanStatusDTO;
import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan.UpdateLoanStatusUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/loans")
public class UpdateLoanStatusController {
  
  @Autowired
  private UpdateLoanStatusUseCase updateLoanStatus;

  @PatchMapping("/{id}")
  public ResponseEntity<Loan> handle(@PathVariable Long id, @RequestBody @Valid UpdateLoanStatusDTO updateLoanStatusDTO) {
    Loan loan = updateLoanStatus.execute(id, updateLoanStatusDTO.status(), updateLoanStatusDTO.alteration());
    return ResponseEntity.ok(loan);
  }

}
