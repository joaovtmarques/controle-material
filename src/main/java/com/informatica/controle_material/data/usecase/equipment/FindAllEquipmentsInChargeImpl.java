package com.informatica.controle_material.data.usecase.equipment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.usecases.equipment.FindAllEquipmentsInChargeUseCase;
import com.informatica.controle_material.infra.repository.EquipmentRepository;

@Service
public class FindAllEquipmentsInChargeImpl implements FindAllEquipmentsInChargeUseCase {
  
  @Autowired
  private EquipmentRepository equipmentRepository;

  @Override
  public List<Equipment> execute(String type) {
    return equipmentRepository.findByIsInChargeAndType(true, type);
  }

}
