package com.informatica.controle_material.presentation.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.auth.AuthRequestDTO;
import com.informatica.controle_material.domain.usecases.auth.AuthUseCase;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthUseCase auth;

  @PostMapping
  public ResponseEntity<String> handle(@RequestBody AuthRequestDTO authRequestDTO) {
    String token = auth.execute(authRequestDTO);
    return ResponseEntity.ok(token);
  }

}