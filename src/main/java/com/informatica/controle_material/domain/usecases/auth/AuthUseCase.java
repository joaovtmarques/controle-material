package com.informatica.controle_material.domain.usecases.auth;

import com.informatica.controle_material.data.dto.auth.AuthRequestDTO;

public interface AuthUseCase {
  
  String execute(AuthRequestDTO authRequestDTO);

}
