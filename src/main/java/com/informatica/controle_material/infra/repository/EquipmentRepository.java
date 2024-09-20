package com.informatica.controle_material.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informatica.controle_material.domain.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
  
  List<Equipment> findByType(String type);

  List<Equipment> findByState(String state);

}
