package com.informatica.controle_material.data.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
  @NotBlank(message = "O email do usuário deve ser informado.")
  String email,
  @NotBlank(message = "A senha do usuário deve ser informada.")
  String password
) {
  
}
