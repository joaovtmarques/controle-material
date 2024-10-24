package com.informatica.controle_material.data.usecase.equipment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_material.data.dto.equipment.UpdateEquipmentDTO;
import com.informatica.controle_material.data.exception.NotFoundException;
import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.usecases.equipment.UpdateEquipmentUseCase;
import com.informatica.controle_material.infra.repository.EquipmentRepository;

@Service
public class UpdateEquipmentImpl implements UpdateEquipmentUseCase {

  @Autowired
  private EquipmentRepository equipmentRepository;

  @Transactional
  @Override
  public Equipment execute(Long id, UpdateEquipmentDTO updateEquipmentDTO) {
    Optional<Equipment> equipmentExists = equipmentRepository.findById(id);

    if (equipmentExists.isEmpty()) {
      throw new NotFoundException("O equipamento " + id + " não existe");
    }

    equipmentExists.get().setCondition(updateEquipmentDTO.condition());
    equipmentExists.get().setIsInCharge(updateEquipmentDTO.isInCharge());

    return equipmentRepository.save(equipmentExists.get());
  }

}
