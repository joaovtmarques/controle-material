package com.informatica.controle_material.domain.usecases.admin;

import java.util.List;

import com.informatica.controle_material.domain.model.User;

public interface FindAllUsersUseCase {
  
  List<User> execute(String type);

}
