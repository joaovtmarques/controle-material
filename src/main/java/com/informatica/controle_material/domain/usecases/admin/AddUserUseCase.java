package com.informatica.controle_material.domain.usecases.admin;

import com.informatica.controle_material.data.dto.admin.AddUserDTO;
import com.informatica.controle_material.domain.model.User;

public interface AddUserUseCase {
  
  User execute(AddUserDTO addUserDTO);

}
