package com.informatica.controle_material.presentation.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.admin.AddUserDTO;
import com.informatica.controle_material.domain.model.User;
import com.informatica.controle_material.domain.usecases.admin.AddUserUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class AddUserController implements ControllerProtocol<AddUserDTO, ResponseEntity<User>> {
  
  @Autowired
  private AddUserUseCase addUser;

  @Override
  @PostMapping
  public ResponseEntity<User> handle(@Valid @RequestBody AddUserDTO addUserDTO) {
    User user = addUser.execute(addUserDTO);

    return ResponseEntity.status(201).body(user);
  }

}
