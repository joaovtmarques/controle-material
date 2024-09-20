package com.informatica.controle_material.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "items")
public class Item {
  
  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, unique = true, nullable = false)
  private Long id;

  @Column(name = "name", unique = true, nullable = false)
  private String name;

  @Column(name = "serial_number", unique = true, nullable = false)
  private String serialNumber;

  @Column(name = "amount", nullable = false)
  private Integer amount;

  @Column(name = "price", nullable = false)
  private Double price;

  @Column(name = "observation", nullable = true)
  private String observation;

  @Column(name = "condition", nullable = false)
  private String condition;

  @JsonIgnore
  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Loan> loans;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false, updatable = false)
  private Category category;

}
