package com.informatica.controle_material.domain.usecases.category;

import java.util.Optional;

import com.informatica.controle_material.domain.model.Category;

public interface FindCategoryByIdUseCase {
  
  Optional<Category> execute(Long id);

}
