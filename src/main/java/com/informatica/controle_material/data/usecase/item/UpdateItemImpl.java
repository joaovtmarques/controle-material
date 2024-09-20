package com.informatica.controle_material.data.usecase.item;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_material.data.exception.NotFoundException;
import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.usecases.item.UpdateItemUseCase;
import com.informatica.controle_material.infra.repository.ItemRepository;

@Service
public class UpdateItemImpl implements UpdateItemUseCase {

  @Autowired
  private ItemRepository itemRepository;

  @Transactional
  @Override
  public Item execute(Long itemId, Item item) {
    Optional<Item> itemExists = itemRepository.findById(itemId);

    if(itemExists.isEmpty()) {
      throw new NotFoundException("O item "+itemId+" não existe");
    }

    itemExists.get().setCategory(item.getCategory());
    itemExists.get().setAmount(item.getAmount());
    itemExists.get().setName(item.getName());
    itemExists.get().setObservation(item.getObservation());
    itemExists.get().setPrice(item.getPrice());
    itemExists.get().setSerialNumber(item.getSerialNumber());
    itemExists.get().setCondition(item.getCondition());
    return itemRepository.save(itemExists.get());
  }

}
