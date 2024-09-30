package com.informatica.controle_material.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informatica.controle_material.domain.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
  
  List<Loan> findByReceiverId(Long receiverId);

  List<Loan> findAllByType(String type);

  List<Loan> findAllByStatusAndType(String status, String type);

}
