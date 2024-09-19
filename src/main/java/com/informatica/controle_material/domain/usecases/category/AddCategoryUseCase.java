package com.informatica.controle_material.domain.usecases.category;

import com.informatica.controle_material.domain.model.Category;

public interface AddCategoryUseCase {
  
  Category execute(String name);

}
