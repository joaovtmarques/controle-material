package com.informatica.controle_material.data.usecase.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.usecases.item.FindAllItemsInChargeUseCase;
import com.informatica.controle_material.infra.repository.ItemRepository;

@Service
public class FindAllItemsInChargeImpl implements FindAllItemsInChargeUseCase {
  
  @Autowired
  private ItemRepository itemRepository;

  @Override
  public List<Item> execute(String type) {
    return itemRepository.findAllByIsInChargeAndType(true, type);
  }

}
