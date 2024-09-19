package com.informatica.controle_material.data.usecase.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.data.exception.AlreadyExistsException;
import com.informatica.controle_material.domain.model.Category;
import com.informatica.controle_material.domain.usecases.category.AddCategoryUseCase;
import com.informatica.controle_material.infra.repository.CategoryRepository;

@Service
public class AddCategoryImpl implements AddCategoryUseCase {
  
  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public Category execute(String name) {
    if (categoryRepository.findByName(name).isPresent()) {
      throw new AlreadyExistsException("O item "+name+" já esta cadastrado");
    }
    Category category = new Category();
    category.setName(name);
    return categoryRepository.save(category);
  }

}
