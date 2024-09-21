package com.informatica.controle_material.presentation.controller.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.category.AddCategoryDTO;
import com.informatica.controle_material.domain.model.Category;
import com.informatica.controle_material.domain.usecases.category.AddCategoryUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/categories")
public class AddCategoryController implements ControllerProtocol<AddCategoryDTO, ResponseEntity<Category>> {
  
  @Autowired
  private AddCategoryUseCase addCategory;

  @Override
  @PostMapping
  public ResponseEntity<Category> handle(@Valid @RequestBody AddCategoryDTO addCategoryDTO) {
    Category category = addCategory.execute(addCategoryDTO);
    return ResponseEntity.status(201).body(category);
  }

}
