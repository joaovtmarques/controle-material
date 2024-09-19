package com.informatica.controle_material.domain.usecases.category;

import java.util.List;

import com.informatica.controle_material.domain.model.Category;

public interface FindAllCategoriesUseCase {
  
  List<Category> execute();

}
