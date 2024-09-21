package com.informatica.controle_material.infra.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informatica.controle_material.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
  Optional<User> findByEmail(String email);

  List<User> findAllByType(String type);

}
