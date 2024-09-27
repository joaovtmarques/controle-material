package com.informatica.controle_material.presentation.controller.loan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan.FindAllLoansUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

@RestController
@RequestMapping("/api/loans")
public class FindAllLoansController implements ControllerProtocol<String, ResponseEntity<List<Loan>>> {
  
  @Autowired
  private FindAllLoansUseCase findAllLoans;

  @GetMapping
  public ResponseEntity<List<Loan>> handle(@RequestParam String type) {
    return ResponseEntity.ok(findAllLoans.execute(type));
  }

}
