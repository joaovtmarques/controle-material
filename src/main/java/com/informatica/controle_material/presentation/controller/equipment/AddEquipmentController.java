package com.informatica.controle_material.presentation.controller.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.equipment.AddEquipmentDTO;
import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.usecases.equipment.AddEquipmentUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/equipments")
public class AddEquipmentController implements ControllerProtocol<AddEquipmentDTO, ResponseEntity<Equipment>> {

  @Autowired
  private AddEquipmentUseCase addEquipment;

  @Override
  @PostMapping
  public ResponseEntity<Equipment> handle(@Valid @RequestBody AddEquipmentDTO addEquipmentDTO) {
    Equipment equipment = addEquipment.execute(addEquipmentDTO);

    return ResponseEntity.status(201).body(equipment);
  }

}
