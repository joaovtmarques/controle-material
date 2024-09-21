package com.informatica.controle_material.domain.usecases.token;

import com.informatica.controle_material.domain.model.User;

public interface TokenUseCase {
  String generateToken(User user);

  String validateToken(String token);

}
