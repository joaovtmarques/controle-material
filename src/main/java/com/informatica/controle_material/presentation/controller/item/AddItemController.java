package com.informatica.controle_material.presentation.controller.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatica.controle_material.data.dto.item.AddItemDTO;
import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.usecases.item.AddItemUseCase;
import com.informatica.controle_material.presentation.controller.protocol.ControllerProtocol;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/items")
public class AddItemController implements ControllerProtocol<AddItemDTO, ResponseEntity<Item>> {

  @Autowired
  private AddItemUseCase addItem;

  @Override
  @PostMapping
  public ResponseEntity<Item> handle(@Valid @RequestBody AddItemDTO addItemDTO) {
    Item item = addItem.execute(addItemDTO);

    return ResponseEntity.status(201).body(item);
  }

}
