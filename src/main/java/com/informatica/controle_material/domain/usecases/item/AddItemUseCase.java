package com.informatica.controle_material.domain.usecases.item;

import com.informatica.controle_material.data.dto.item.AddItemDTO;
import com.informatica.controle_material.domain.model.Item;

public interface AddItemUseCase {
  
  Item execute(AddItemDTO addItemDTO);

}
