package com.informatica.controle_material.infra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informatica.controle_material.domain.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
  
  Optional<Equipment> findByName(String name);

  List<Equipment> findByType(String type);

  List<Equipment> findByState(String state);

  List<Equipment> findByIsInChargeAndType(Boolean isInCharge, String type);

  List<Equipment> findByAmountGreaterThanEqualAndType(Integer amount, String type);

}
