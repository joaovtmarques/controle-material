package com.informatica.controle_material.presentation.controller.loan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.loan.FindLoanByStatusDTO;
import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan.FindAllLoansByStatusUseCase;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/loans/open")
public class FindAllLoansByStatusController {

  @Autowired
  private FindAllLoansByStatusUseCase findAllOpenLoans;

  @GetMapping
  public ResponseEntity<List<Loan>> handle(@RequestParam String status, @RequestParam String type) {
    return ResponseEntity.ok(findAllOpenLoans.execute(new FindLoanByStatusDTO(status, type)));
  }

}
