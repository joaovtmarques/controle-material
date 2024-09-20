package com.informatica.controle_material.presentation.controller.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.usecases.item.UpdateItemUseCase;

@RestController
@RequestMapping("/api/items")
public class UpdateItemController {
  
  @Autowired
  private UpdateItemUseCase updateItem;

  @PutMapping("/{itemId}")
  public ResponseEntity<Item> handle(@PathVariable Long itemId, @RequestBody Item item) {
    Item updatedItem = updateItem.execute(itemId,item);
    return ResponseEntity.ok(updatedItem);
  }

}
