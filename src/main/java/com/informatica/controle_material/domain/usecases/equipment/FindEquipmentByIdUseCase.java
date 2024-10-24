package com.informatica.controle_material.domain.usecases.equipment;

import java.util.Optional;

import com.informatica.controle_material.domain.model.Equipment;

public interface FindEquipmentByIdUseCase {

  Optional<Equipment> execute(Long id);

}
