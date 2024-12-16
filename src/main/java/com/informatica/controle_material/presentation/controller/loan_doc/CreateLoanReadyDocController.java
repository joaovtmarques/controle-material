package com.informatica.controle_material.presentation.controller.loan_doc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.loan.LoanEquipmentDTO;
import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.usecases.equipment.FindEquipmentByTypeUseCase;
import com.informatica.controle_material.domain.usecases.loan.GetAllLoansWithEquipmentsUseCase;
import com.informatica.controle_material.domain.usecases.loan_doc.CreateLoanReadyDocUseCase;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/loan-doc/ready")
public class CreateLoanReadyDocController {

  @Autowired
  private GetAllLoansWithEquipmentsUseCase getAllLoansWithEquipments;

  @Autowired
  private CreateLoanReadyDocUseCase createLoanReadyDocUseCase;

  @Autowired
  private FindEquipmentByTypeUseCase findAllEquipments;

  @GetMapping
  public ResponseEntity<String> execute(@RequestParam String type) throws Exception {
    List<LoanEquipmentDTO> loans = getAllLoansWithEquipments.execute();

    List<Equipment> equipments = findAllEquipments.execute(type);

    String filepath = createLoanReadyDocUseCase.execute(loans, equipments);

    return ResponseEntity.ok(filepath);
  }

}
