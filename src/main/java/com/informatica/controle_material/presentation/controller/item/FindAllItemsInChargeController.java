package com.informatica.controle_material.presentation.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.usecases.item.FindAllItemsInChargeUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

@RestController
@RequestMapping("/api/items/isincharge")
public class FindAllItemsInChargeController implements ControllerProtocol<String, ResponseEntity<List<Item>>> {
  
  @Autowired
  private FindAllItemsInChargeUseCase findAllItemsInCharge;

  @GetMapping
  public ResponseEntity<List<Item>> handle(@RequestParam String type) {
    return ResponseEntity.ok(findAllItemsInCharge.execute(type));
  }

}
