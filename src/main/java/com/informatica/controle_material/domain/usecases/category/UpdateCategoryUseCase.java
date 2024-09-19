package com.informatica.controle_material.domain.usecases.category;

import com.informatica.controle_material.domain.model.Category;

public interface UpdateCategoryUseCase {
  
  Category execute(Long categoryId, Category category);

} 
