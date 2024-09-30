package com.informatica.controle_material.presentation.controller.loan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.loan.UpdateLoanStatusDTO;
import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan.FindAllLoansByAlterationUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/loans/alteration")
public class FindAllLoansByAlterationController {
  
  @Autowired
  private FindAllLoansByAlterationUseCase findAllLoansByAlteration;

  @GetMapping
  public ResponseEntity<List<Loan>> handle(
    @RequestParam Boolean alteration,
    @RequestParam String type
  ) {
    return ResponseEntity.ok(findAllLoansByAlteration.execute(alteration, type));
  }

}
