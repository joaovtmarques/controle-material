package com.informatica.controle_material.presentation.controller.equipment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.usecases.equipment.FindAllEquipmentsUseCase;

@RestController
@RequestMapping("/api/equipments")
public class FindaAllEquipmentsController {
  
  @Autowired
  private FindAllEquipmentsUseCase findAllEquipments;

  @GetMapping
  public ResponseEntity<List<Equipment>> handle() {
    List<Equipment> equipments = findAllEquipments.execute();

    return ResponseEntity.ok(equipments);
  }

}
