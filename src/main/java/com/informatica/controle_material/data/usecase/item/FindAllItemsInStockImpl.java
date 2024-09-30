package com.informatica.controle_material.data.usecase.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.usecases.item.FindAllItemsInStockUseCase;
import com.informatica.controle_material.infra.repository.ItemRepository;

@Service
public class FindAllItemsInStockImpl implements FindAllItemsInStockUseCase {
  
  @Autowired
  private ItemRepository itemRepository;

  @Override
  public List<Item> execute(String type) {
    return itemRepository.findByAmountGreaterThanEqualAndType(1, type);
  }

}
