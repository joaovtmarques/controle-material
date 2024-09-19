package com.informatica.controle_material.data.usecase.category;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_material.data.exception.NotFoundException;
import com.informatica.controle_material.domain.model.Category;
import com.informatica.controle_material.domain.usecases.category.UpdateCategoryUseCase;
import com.informatica.controle_material.infra.repository.CategoryRepository;

@Service
public class UpdateCategoryImpl implements UpdateCategoryUseCase {
  
  @Autowired
  private CategoryRepository categoryRepository;

  @Transactional
  @Override
  public Category execute(Long categoryId, Category category) {
    Optional<Category> categoryExists = categoryRepository.findById(categoryId);

    if(categoryExists.isEmpty()) {
      throw new NotFoundException("A categoria "+categoryId+" não existe");
    }

    categoryExists.get().setName(category.getName());
    return categoryRepository.save(categoryExists.get());
  }

}
