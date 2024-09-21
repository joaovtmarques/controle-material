package com.informatica.controle_material.data.usecase.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.data.exception.BadRequestException;
import com.informatica.controle_material.domain.model.User;
import com.informatica.controle_material.domain.usecases.admin.FindAllUsersUseCase;
import com.informatica.controle_material.infra.repository.UserRepository;

@Service
public class FindAllUsersImpl implements FindAllUsersUseCase {

  @Autowired
  private UserRepository userRepository;

  public List<User> execute(String type) {
    try {
      return userRepository.findAllByType(type);
    } catch (Exception e) {
      throw new BadRequestException("Erro ao buscar os usuarios");
    }
  } 
}
