package com.informatica.controle_material.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informatica.controle_material.domain.model.Receiver;

public interface ReceiverRepository extends JpaRepository<Receiver, Long> {
  
}
