package com.informatica.controle_material.domain.model;

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
@Table(name = "loans")
public class Loan {
  
  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, unique = true, nullable = false)
  private Long id;

  @Column(name = "amount", nullable = false)
  private Integer amount;

  @Column(name = "date", nullable = false)
  private String date;

  @Column(name = "observation")
  private String observation;

  @Column(name = "devolution_date")
  private String devolutionDate;

  @Column(name = "status", columnDefinition = "varchar(25) default 'ABERTO'")
  private String status;

  @Column(name = "type", nullable = false)
  private String type;

  @ManyToOne
  @JoinColumn(name = "lender_id", nullable = false, updatable = false)
  private User lender;

  @ManyToOne
  @JoinColumn(name = "receiver_id", nullable = false, updatable = false)
  private Receiver receiver;

  @ManyToOne
  @JoinColumn(name = "item_id", nullable = true, updatable = false)
  private Item item;

  @ManyToOne
  @JoinColumn(name = "equipment_id", nullable = true,updatable = false)
  private Equipment equipment;

}
