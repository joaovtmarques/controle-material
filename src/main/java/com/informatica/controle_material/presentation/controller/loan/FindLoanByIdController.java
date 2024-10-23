package com.informatica.controle_material.presentation.controller.loan;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.usecases.loan.FindLoanByIdUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

@CrossOrigin("*")
@RestController
@RequestMapping("/loans")
public class FindLoanByIdController implements ControllerProtocol<Long, ResponseEntity<Optional<Loan>>> {

  @Autowired
  private FindLoanByIdUseCase findLoanById;

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<Optional<Loan>> handle(@PathVariable Long id) {
    return ResponseEntity.ok(findLoanById.execute(id));
  }

}
