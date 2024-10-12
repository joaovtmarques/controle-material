package com.informatica.controle_material.domain.usecases.auth;

import com.informatica.controle_material.data.dto.auth.AuthRequestDTO;
import com.informatica.controle_material.data.dto.auth.AuthResponseDTO;

public interface AuthUseCase {

  AuthResponseDTO execute(AuthRequestDTO authRequestDTO);

}
