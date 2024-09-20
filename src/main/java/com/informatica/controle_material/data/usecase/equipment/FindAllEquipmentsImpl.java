package com.informatica.controle_material.data.usecase.equipment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.data.exception.BadRequestException;
import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.usecases.equipment.FindAllEquipmentsUseCase;
import com.informatica.controle_material.infra.repository.EquipmentRepository;

@Service
public class FindAllEquipmentsImpl implements FindAllEquipmentsUseCase {
  
  @Autowired
  private EquipmentRepository equipmentRepository;

  @Override
  public List<Equipment> execute() {
    try {
      return equipmentRepository.findAll();
    } catch (Exception e) {
      throw new BadRequestException("Erro ao buscar os equipamentos");
    }
  }

}
