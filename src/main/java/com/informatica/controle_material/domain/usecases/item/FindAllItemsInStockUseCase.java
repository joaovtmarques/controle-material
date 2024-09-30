package com.informatica.controle_material.domain.usecases.item;

import java.util.List;

import com.informatica.controle_material.domain.model.Item;

public interface FindAllItemsInStockUseCase {
  
  List<Item> execute(String type);

}
