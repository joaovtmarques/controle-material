package com.informatica.controle_material.data.usecase.category;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.informatica.controle_material.data.exception.BadRequestException;
import com.informatica.controle_material.domain.model.Category;
import com.informatica.controle_material.domain.usecases.category.FindCategoryByIdUseCase;
import com.informatica.controle_material.infra.repository.CategoryRepository;

public class FindCategoryByIdImpl implements FindCategoryByIdUseCase {
  
  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public Optional<Category> execute(Long id) {
    try {
      return categoryRepository.findById(id);
    } catch (Exception e) {
      throw new BadRequestException("Erro ao buscar a categoria");
    }
  }

}
