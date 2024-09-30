package com.informatica.controle_material.presentation.controller.loan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan.FindAllOpenLoansUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

@RestController
@RequestMapping("/api/loans/open")
public class FindAllOpenLoansController implements ControllerProtocol<String, ResponseEntity<List<Loan>>> {
  
  @Autowired
  private FindAllOpenLoansUseCase findAllOpenLoans;

  @Override
  @GetMapping
  public ResponseEntity<List<Loan>> handle(@RequestParam String type) {
    return ResponseEntity.ok(findAllOpenLoans.execute(type));
  }

}
