package com.informatica.controle_material.data.dto.category;

import com.informatica.controle_material.domain.model.Category;

import jakarta.validation.constraints.NotBlank;

public record AddCategoryDTO(
  @NotBlank(message = "O nome da categoria deve ser informado.")
  String name,
  @NotBlank(message = "O tipo da categoria deve ser informado.")
  String type
) {

  public Category toModel() {
    Category category = new Category();
    category.setName(name);
    category.setType(type);

    return category;
  }

}
