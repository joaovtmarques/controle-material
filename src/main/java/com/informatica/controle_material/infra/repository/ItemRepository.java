package com.informatica.controle_material.infra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informatica.controle_material.domain.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

  Optional<Item> findByName(String name);

  List<Item> findAllByType(String type);

}
