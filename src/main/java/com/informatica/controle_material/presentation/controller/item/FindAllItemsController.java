package com.informatica.controle_material.presentation.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.usecases.item.FindAllItemsUseCase;

@RestController
@RequestMapping("/api/items")
public class FindAllItemsController {
  
  @Autowired
  private FindAllItemsUseCase findAllItems;

  @GetMapping
  public ResponseEntity<List<Item>> handle() {
    List<Item> items = findAllItems.execute();

    return ResponseEntity.ok(items);
  }

}
