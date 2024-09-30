package com.informatica.controle_material.domain.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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

  @Column(name = "alteration", nullable = false, columnDefinition = "boolean default false")
  private Boolean alteration;

  @ManyToOne
  @JoinColumn(name = "lender_id", nullable = false, updatable = false)
  private User lender;

  @ManyToOne
  @JoinColumn(name = "receiver_id", nullable = false, updatable = false)
  private Receiver receiver;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Item> items;

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Equipment> equipments;

  @ManyToOne
  @JoinColumn(name = "loan_doc_id", nullable = true, updatable = false)
  private LoanDoc loanDoc;

}
