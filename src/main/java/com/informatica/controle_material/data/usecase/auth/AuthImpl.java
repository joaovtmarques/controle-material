package com.informatica.controle_material.data.usecase.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.data.dto.auth.AuthRequestDTO;
import com.informatica.controle_material.data.exception.NotFoundException;
import com.informatica.controle_material.data.exception.UnauthorizedException;
import com.informatica.controle_material.domain.model.User;
import com.informatica.controle_material.domain.usecases.auth.AuthUseCase;
import com.informatica.controle_material.domain.usecases.token.TokenUseCase;
import com.informatica.controle_material.infra.repository.UserRepository;

@Service
public class AuthImpl implements AuthUseCase {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private TokenUseCase tokenService;

  @Override
  public String execute(AuthRequestDTO authRequestDTO) {
    Optional<User> userExists = userRepository.findByEmail(authRequestDTO.email());
    
    if(userExists.isEmpty()) {
      throw new NotFoundException("O usuário com o email: "+authRequestDTO.email()+" não existe");
    }

    if(!passwordEncoder.matches(authRequestDTO.password(), userExists.get().getPassword())) {
      throw new UnauthorizedException("As credenciais de login não são válidas");
    }

    return tokenService.generateToken(userExists.get());
  }
  
}
