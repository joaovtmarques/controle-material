package com.informatica.controle_material.data.dto.item;

import com.informatica.controle_material.domain.model.Category;
import com.informatica.controle_material.domain.model.Item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddItemDTO(
  @NotBlank(message = "O nome do item deve ser informado.")
  String name, 
  String serialNumber, 
  @NotNull(message = "Informe a quantidade do item")
  Integer amount,
  @NotNull(message = "Informe o preço do item")
  Double price,
  @NotBlank(message = "Uma observação do item deve ser informada")
  String observation,
  @NotBlank(message = "Um número de série válido deve ser informado")
  String condition,
  @NotBlank(message = "Um tipo de item válido deve ser informado")
  String type,
  @NotNull(message = "Informe se o item está em carga")
  Boolean isInCharge,
  @NotNull(message = "Uma categoria válida deve ser informada")
  Long categoryId
) {

  public Item toModel(Category category) {
    Item item = new Item();
    item.setCategory(category);
    item.setAmount(amount);
    item.setName(name);
    item.setObservation(observation);
    item.setPrice(price);
    item.setSerialNumber(serialNumber);
    item.setCondition(condition);
    item.setType(type);
    item.setIsInCharge(isInCharge);

    return item;
  }

}
