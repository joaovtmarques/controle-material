package com.informatica.controle_material.data.dto.category;

import jakarta.validation.constraints.NotBlank;

public record AddCategoryDTO(
  @NotBlank(message = "O nome da categoria deve ser informado.")
  String name
) {}
