package com.informatica.controle_material.presentation.controller.equipment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.usecases.equipment.FindEquipmentByIdUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/equipments")
public class FindEquipmentByIdController implements ControllerProtocol<Long, ResponseEntity<Optional<Equipment>>> {

  @Autowired
  private FindEquipmentByIdUseCase findEquipmentById;

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Equipment>> handle(@PathVariable Long id) {
    return ResponseEntity.ok(findEquipmentById.execute(id));
  }

}
