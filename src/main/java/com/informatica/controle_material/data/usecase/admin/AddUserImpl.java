package com.informatica.controle_material.data.usecase.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_material.data.dto.admin.AddUserDTO;
import com.informatica.controle_material.data.exception.AlreadyExistsException;
import com.informatica.controle_material.domain.model.User;
import com.informatica.controle_material.domain.usecases.admin.AddUserUseCase;
import com.informatica.controle_material.infra.repository.UserRepository;

@Service
public class AddUserImpl implements AddUserUseCase {
  
  @Autowired
  private UserRepository userRepository;

  @Autowired
  @Qualifier("bCryptPasswordEncoder")
  private PasswordEncoder passwordEncoder;
  
  @Transactional
  @Override
  public User execute(AddUserDTO addUserDTO) {
    if (userRepository.findByEmail(addUserDTO.cpf()).isPresent()) {
      throw new AlreadyExistsException("O usuario já está cadastrado");
    }
    User user = addUserDTO.toModel(passwordEncoder.encode(addUserDTO.password()));
    
    return userRepository.save(user);
  }

}
