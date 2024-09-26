package com.informatica.controle_material.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informatica.controle_material.domain.model.LoanDoc;

public interface LoanDocRepository extends JpaRepository<LoanDoc, Long> {
  
}
