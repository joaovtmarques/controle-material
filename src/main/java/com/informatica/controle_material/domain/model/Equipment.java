package com.informatica.controle_material.domain.model;

import java.math.BigDecimal;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "equipments")
public class Equipment {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, unique = true, nullable = false)
  private Long id;

  @Column(name = "name", unique = true, nullable = false)
  private String name;

  @Column(name = "amount", nullable = false)
  private Integer amount;

  @Column(name = "total_price", nullable = false)
  private BigDecimal price;

  @Column(name = "serial_number", unique = true, nullable = true)
  private String serialNumber;

  @Column(name = "observation", nullable = true)
  private String observation;

  @Column(name = "type", nullable = false)
  private String type;

  @Column(name = "state", nullable = false)
  private String state;

  @Column(name = "is_in_charge", nullable = false)
  private Boolean isInCharge;

  @Column(name = "is_temporary", nullable = false)
  private Boolean isTemporary;

  @Column(name = "owner", nullable = true)
  private String owner;

  @Column(name = "condition", nullable = false)
  private String condition;

  @Column(name = "amount_out", nullable = true)
  private Integer amountOut;

  @Column(name = "loan_date", nullable = true)
  private String loanDate;

  @Column(name = "lender", nullable = true)
  private String lender;

  @Column(name = "receiver", nullable = true)
  private String receiver;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false, updatable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Category category;

}
