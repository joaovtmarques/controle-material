package com.informatica.controle_material.infra.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.informatica.controle_material.domain.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  Optional<Category> findByName(String name);

  List<Category> findByType(String type);

}
