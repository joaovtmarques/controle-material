package com.informatica.controle_material.presentation.controller.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.usecases.equipment.DeleteEquipmentUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

@RestController
@RequestMapping("/api/equipments")
public class DeleteEquipmentController implements ControllerProtocol<Long, ResponseEntity<Void>> {
  
  @Autowired
  private DeleteEquipmentUseCase deleteEquipment;

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> handle(@PathVariable Long id) {
    deleteEquipment.execute(id);
    
    return ResponseEntity.ok().build();
  }

}
