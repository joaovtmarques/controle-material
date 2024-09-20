package com.informatica.controle_material.data.usecase.item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_material.data.dto.item.AddItemDTO;
import com.informatica.controle_material.data.exception.AlreadyExistsException;
import com.informatica.controle_material.data.exception.NotFoundException;
import com.informatica.controle_material.domain.model.Category;
import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.usecases.item.AddItemUseCase;
import com.informatica.controle_material.infra.repository.CategoryRepository;
import com.informatica.controle_material.infra.repository.ItemRepository;

@Service
public class AddItemImpl implements AddItemUseCase {
  
  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Transactional
  @Override
  public Item execute(AddItemDTO addItemDTO) {
    Optional<Category> categoryExists = categoryRepository.findById(addItemDTO.categoryId());
    if(categoryExists.isEmpty()) {
      throw new NotFoundException("A categoria "+addItemDTO.categoryId()+" não existe");
    }

    Optional<Item> itemAlreadyExists = itemRepository.findByName(addItemDTO.name());
    if(itemAlreadyExists.isPresent()) {
      throw new AlreadyExistsException("O item "+addItemDTO.name()+" já está cadastrado");
    }

    Item item = addItemDTO.toModel(categoryExists.get());
    return itemRepository.save(item);
  }

}
