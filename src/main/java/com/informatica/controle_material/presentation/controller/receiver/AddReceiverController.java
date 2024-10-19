package com.informatica.controle_material.presentation.controller.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.receiver.AddReceiverDTO;
import com.informatica.controle_material.domain.model.Receiver;
import com.informatica.controle_material.domain.usecases.receiver.AddReceiverUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/receivers")
public class AddReceiverController implements ControllerProtocol<AddReceiverDTO, ResponseEntity<Receiver>> {

  @Autowired
  private AddReceiverUseCase addReceiver;

  @Override
  @PostMapping
  public ResponseEntity<Receiver> handle(@Valid @RequestBody AddReceiverDTO addReceiverDTO) {
    Receiver receiver = addReceiver.execute(addReceiverDTO);

    return ResponseEntity.status(201).body(receiver);
  }

}
