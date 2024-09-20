package com.informatica.controle_material.data.dto.equipment;

import com.informatica.controle_material.domain.model.Category;
import com.informatica.controle_material.domain.model.Equipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddEquipmentDTO(
  @NotBlank(message = "O nome do item deve ser informado.")
  String name,
  String serialNumber,
  @NotNull(message = "Informe a quantidade disponível do equipamento")
  Integer amount,
  @NotNull(message = "Informe o preço do equipamento")
  Double totalPrice,
  @NotBlank(message = "Uma observação do equipamento deve ser informada")
  String observation,
  @NotBlank(message = "Um tipo de equipamento válido deve ser informado")
  String type,
  @NotBlank(message = "Um estado válido deve ser informado")
  String state,
  @NotNull(message = "Uma categoria válida deve ser informada")
  Long categoryId
) {
  
  public Equipment toModel(Category category) {
    Equipment equipment = new Equipment();
    equipment.setAmount(amount);
    equipment.setCategory(category);
    equipment.setName(name);
    equipment.setObservation(observation);
    equipment.setSerialNumber(serialNumber);
    equipment.setTotalPrice(totalPrice);
    equipment.setType(type);
    equipment.setState(state);

    return equipment;
  }

}
