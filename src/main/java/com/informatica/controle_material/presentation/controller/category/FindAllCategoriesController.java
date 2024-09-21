package com.informatica.controle_material.presentation.controller.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.Category;
import com.informatica.controle_material.domain.usecases.category.FindAllCategoriesUseCase;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/categories")
public class FindAllCategoriesController {
  
  @Autowired
  private FindAllCategoriesUseCase findAllCategories;

  @GetMapping
  public ResponseEntity<List<Category>> handle(@RequestParam String type) {
    List<Category> categories = findAllCategories.execute(type);

    return ResponseEntity.ok(categories);
  }
  
}
