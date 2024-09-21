package com.informatica.controle_material.presentation.controller.receiver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.Receiver;
import com.informatica.controle_material.domain.usecases.receiver.FindAllReceiversUseCase;

@RestController
@RequestMapping("/api/receivers")
public class FindAllReceiversController {
  
  @Autowired
  private FindAllReceiversUseCase findAllReceivers;

  @GetMapping
  public ResponseEntity<List<Receiver>> handle() {
    List<Receiver> receivers = findAllReceivers.execute();

    return ResponseEntity.ok(receivers);
  }

}
