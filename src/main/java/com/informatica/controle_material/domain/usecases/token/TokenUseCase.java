package com.informatica.controle_material.domain.usecases.token;

import com.informatica.controle_material.data.dto.auth.AuthResponseDTO;
import com.informatica.controle_material.domain.model.User;

public interface TokenUseCase {
  AuthResponseDTO generateToken(User user);

  String validateToken(String token);

}
