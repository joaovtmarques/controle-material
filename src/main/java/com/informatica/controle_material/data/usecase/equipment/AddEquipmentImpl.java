package com.informatica.controle_material.data.usecase.equipment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_material.data.dto.equipment.AddEquipmentDTO;
import com.informatica.controle_material.data.exception.AlreadyExistsException;
import com.informatica.controle_material.data.exception.NotFoundException;
import com.informatica.controle_material.domain.model.Category;
import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.usecases.equipment.AddEquipmentUseCase;
import com.informatica.controle_material.infra.repository.CategoryRepository;
import com.informatica.controle_material.infra.repository.EquipmentRepository;

@Service
public class AddEquipmentImpl implements AddEquipmentUseCase {
  
  @Autowired
  private EquipmentRepository equipmentRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Transactional
  @Override
  public Equipment execute(AddEquipmentDTO addEquipmentDTO) {
    Optional<Category> categoryExists = categoryRepository.findById(addEquipmentDTO.categoryId());
    
    if(categoryExists.isEmpty()) {
      throw new NotFoundException("A categoria "+addEquipmentDTO.categoryId()+" não existe");
    }

    Optional<Equipment> equipmentAlreadyExists = equipmentRepository.findByName(addEquipmentDTO.name());
    
    if(equipmentAlreadyExists.isPresent()) {
      throw new AlreadyExistsException("O equipamento "+addEquipmentDTO.name()+" já está cadastrado");
    }

    Equipment equipment = addEquipmentDTO.toModel(categoryExists.get());

    return equipmentRepository.save(equipment);
  }

}
