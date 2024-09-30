package com.informatica.controle_material.presentation.controller.equipment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.usecases.equipment.FindAllEquipmentsInChargeUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

@RestController
@RequestMapping("/api/equipments/isincharge")
public class FindAllEquipmentsInChargeController implements ControllerProtocol<String, ResponseEntity<List<Equipment>>> {
  
  @Autowired
  private FindAllEquipmentsInChargeUseCase findAllEquipmentsInCharge;

  @GetMapping
  public ResponseEntity<List<Equipment>> handle(@RequestParam String type) {
    return ResponseEntity.ok(findAllEquipmentsInCharge.execute(type));
  }

}
