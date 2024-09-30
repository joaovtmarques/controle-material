package com.informatica.controle_material.presentation.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.usecases.item.FindAllItemsInStockUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

@RestController
@RequestMapping("/api/items/in-stock")
public class FindAllItemsInStockController implements ControllerProtocol<String, ResponseEntity<List<Item>>> {
  
  @Autowired
  private FindAllItemsInStockUseCase findAllItemsInStock; 

  @Override
  @GetMapping
  public ResponseEntity<List<Item>> handle(@RequestParam String type) {
    return ResponseEntity.ok(findAllItemsInStock.execute(type));
  }

}
