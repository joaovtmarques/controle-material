package com.informatica.controle_material.data.usecase.equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_material.data.exception.BadRequestException;
import com.informatica.controle_material.domain.usecases.equipment.DeleteEquipmentUseCase;
import com.informatica.controle_material.infra.repository.EquipmentRepository;

@Service
public class DeleteEquipmentImpl implements DeleteEquipmentUseCase {
  
  @Autowired
  private EquipmentRepository equipmentRepository;

  @Transactional
  @Override
  public void execute(Long id) {
    try {
      equipmentRepository.deleteById(id);
    } catch (Exception e) { 
      throw new BadRequestException("Erro ao deletar o equipamento");
    }
  }

}
