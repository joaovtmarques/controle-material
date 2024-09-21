package com.informatica.controle_material.presentation.controller.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.usecases.receiver.DeleteReceiverUseCase;

@RestController
@RequestMapping("/api/receivers")
public class DeleteReceiverController {
  
  @Autowired
  private DeleteReceiverUseCase deleteReceiver;

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> handle(@PathVariable Long id) {
    deleteReceiver.execute(id);
    return ResponseEntity.noContent().build();
  }

}
