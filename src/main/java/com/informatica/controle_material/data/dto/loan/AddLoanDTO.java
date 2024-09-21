package com.informatica.controle_material.data.dto.loan;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.informatica.controle_material.domain.model.Equipment;
import com.informatica.controle_material.domain.model.Item;
import com.informatica.controle_material.domain.model.Loan;
import com.informatica.controle_material.domain.model.Receiver;
import com.informatica.controle_material.domain.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddLoanDTO(
  @NotNull(message = "Informe a quantidade do item")
  Integer amount,
  @NotBlank(message = "Uma observação da cautela deve ser informada")
  String observation,
  String devolutionDate,
  @NotBlank(message = "Um tipo de cautela deve ser informado")
  String type,
  @NotNull(message = "Informe o responsável da cautela")
  Long lenderId,
  @NotNull(message = "Informe o recebedor da cautela")
  Long receiverId,
  Long itemId,
  Long equipmentId
) {

  public Loan toModelWithEquipment(Equipment equipment, User user, Receiver receiver) {
    Loan loan = new Loan();
    loan.setAmount(this.amount);
    loan.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
    loan.setObservation(this.observation);
    loan.setDevolutionDate(this.devolutionDate());
    loan.setStatus("ABERTO");
    loan.setType(this.type);
    loan.setLender(user);
    loan.setReceiver(receiver);
    loan.setEquipment(equipment);
    return loan;
  }

  public Loan toModelWithItem(Item item, User user, Receiver receiver) {
    Loan loan = new Loan();
    loan.setAmount(this.amount);
    loan.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
    loan.setObservation(this.observation);
    loan.setDevolutionDate(this.devolutionDate);
    loan.setStatus("ABERTO");
    loan.setType(this.type);
    loan.setLender(user);
    loan.setReceiver(receiver);
    loan.setItem(item);
    return loan;
  }
  
}
