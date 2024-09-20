package com.informatica.controle_material.data.usecase.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.data.exception.BadRequestException;
import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.usecases.item.FindAllItemsUseCase;
import com.informatica.controle_material.infra.repository.ItemRepository;

@Service
public class FindAllItemsImpl implements FindAllItemsUseCase {
  
  @Autowired
  private ItemRepository itemRepository;

  @Override
  public List<Item> execute() {
    try {
      return itemRepository.findAll();
    } catch (Exception e) {
      throw new BadRequestException("Erro ao buscar os items");
    }
  }

}
