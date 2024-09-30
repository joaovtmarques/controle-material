package com.informatica.controle_material.domain.usecases.equipment;

import java.util.List;

import com.informatica.controle_material.domain.model.Equipment;

public interface FindAllEquipmentsInChargeUseCase {
  
  List<Equipment> execute(String type);

}
