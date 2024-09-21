package com.informatica.controle_material.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informatica.controle_material.domain.model.Receiver;

public interface ReceiverRepository extends JpaRepository<Receiver, Long> {
  
  Optional<Receiver> findByCpf(String cpf);

}
