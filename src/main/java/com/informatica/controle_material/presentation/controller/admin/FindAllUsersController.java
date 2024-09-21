package com.informatica.controle_material.presentation.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.User;
import com.informatica.controle_material.domain.usecases.admin.FindAllUsersUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

@RestController
@RequestMapping("/api/users")
public class FindAllUsersController implements ControllerProtocol<String, ResponseEntity<List<User>>> {
  
  @Autowired
  private FindAllUsersUseCase findAllUsers;

  @GetMapping
  public ResponseEntity<List<User>> handle(@RequestParam String type) {
    List<User> users = findAllUsers.execute(type);
    return ResponseEntity.ok(users);
  }

}
