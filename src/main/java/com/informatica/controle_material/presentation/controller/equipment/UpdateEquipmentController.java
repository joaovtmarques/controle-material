package com.informatica.controle_material.presentation.controller.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.equipment.UpdateEquipmentDTO;
import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.usecases.equipment.UpdateEquipmentUseCase;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/equipments")
public class UpdateEquipmentController {

  @Autowired
  private UpdateEquipmentUseCase updateEquipment;

  @PatchMapping("/{id}")
  public ResponseEntity<Equipment> handle(@PathVariable Long id,
      @RequestBody @Valid UpdateEquipmentDTO updateEquipmentDTO) {
    Equipment equipment = updateEquipment.execute(id, updateEquipmentDTO);
    return ResponseEntity.ok(equipment);
  }

}
