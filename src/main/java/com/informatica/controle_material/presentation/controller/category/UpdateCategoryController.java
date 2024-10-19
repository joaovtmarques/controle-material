package com.informatica.controle_material.presentation.controller.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.Category;
import com.informatica.controle_material.domain.usecases.category.UpdateCategoryUseCase;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/categories")
public class UpdateCategoryController {

  @Autowired
  private UpdateCategoryUseCase updateCategory;

  @PutMapping("/{categoryId}")
  public ResponseEntity<Category> handle(@PathVariable Long categoryId, @RequestBody Category category) {
    return ResponseEntity.ok(updateCategory.execute(categoryId, category));
  }

}
