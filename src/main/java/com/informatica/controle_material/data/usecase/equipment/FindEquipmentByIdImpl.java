package com.informatica.controle_material.data.usecase.equipment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.usecases.equipment.FindEquipmentByIdUseCase;
import com.informatica.controle_material.infra.repository.EquipmentRepository;

@Service
public class FindEquipmentByIdImpl implements FindEquipmentByIdUseCase {

  @Autowired
  private EquipmentRepository equipmentRepository;

  @Override
  public Optional<Equipment> execute(Long id) {
    return equipmentRepository.findById(id);
  }

}
