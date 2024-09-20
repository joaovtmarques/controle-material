package com.informatica.controle_material.domain.usecases.equipment;

import com.informatica.controle_material.data.dto.equipment.AddEquipmentDTO;
import com.informatica.controle_material.domain.model.Equipment;

public interface AddEquipmentUseCase {
  
  Equipment execute(AddEquipmentDTO addEquipmentDTO);

}
