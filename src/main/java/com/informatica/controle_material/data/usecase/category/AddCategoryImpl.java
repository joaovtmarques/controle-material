package com.informatica.controle_material.data.usecase.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_material.data.dto.category.AddCategoryDTO;
import com.informatica.controle_material.data.exception.AlreadyExistsException;
import com.informatica.controle_material.domain.model.Category;
import com.informatica.controle_material.domain.usecases.category.AddCategoryUseCase;
import com.informatica.controle_material.infra.repository.CategoryRepository;

@Service
public class AddCategoryImpl implements AddCategoryUseCase {
  
  @Autowired
  private CategoryRepository categoryRepository;

  @Transactional
  @Override
  public Category execute(AddCategoryDTO addCategoryDTO) {
    if (categoryRepository.findByName(addCategoryDTO.name()).isPresent()) {
      throw new AlreadyExistsException("O item "+addCategoryDTO.name()+" já esta cadastrado");
    }
    Category category = addCategoryDTO.toModel();
    
    return categoryRepository.save(category);
  }

}
