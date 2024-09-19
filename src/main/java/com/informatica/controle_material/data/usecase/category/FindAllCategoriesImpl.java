package com.informatica.controle_material.data.usecase.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.domain.model.Category;
import com.informatica.controle_material.domain.usecases.category.FindAllCategoriesUseCase;
import com.informatica.controle_material.infra.repository.CategoryRepository;

@Service
public class FindAllCategoriesImpl implements FindAllCategoriesUseCase {
  
  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public List<Category> execute() {
    return categoryRepository.findAll();
  }

}
