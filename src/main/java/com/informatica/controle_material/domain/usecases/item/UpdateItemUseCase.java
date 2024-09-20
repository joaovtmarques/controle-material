package com.informatica.controle_material.domain.usecases.item;

import com.informatica.controle_material.domain.model.Item;

public interface UpdateItemUseCase {
  
  Item execute(Long itemId, Item item);

}
