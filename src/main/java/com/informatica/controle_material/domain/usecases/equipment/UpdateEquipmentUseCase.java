package com.informatica.controle_material.domain.usecases.equipment;

import com.informatica.controle_material.data.dto.equipment.UpdateEquipmentDTO;
import com.informatica.controle_material.domain.model.Equipment;

public interface UpdateEquipmentUseCase {

  Equipment execute(Long id, UpdateEquipmentDTO updateEquipmentDTO);

}
