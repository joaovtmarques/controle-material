package com.informatica.controle_material.presentation.controller.equipment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.usecases.equipment.FindEquipmentByStateUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/equipments/state")
public class FindEquipmentByStateController implements ControllerProtocol<String, ResponseEntity<List<Equipment>>> {

  @Autowired
  private FindEquipmentByStateUseCase findEquipmentByState;

  @Override
  @GetMapping
  public ResponseEntity<List<Equipment>> handle(@RequestParam String state) {
    List<Equipment> equipments = findEquipmentByState.execute(state);

    return ResponseEntity.ok(equipments);
  }

}
