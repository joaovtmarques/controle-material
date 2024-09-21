package com.informatica.controle_material.data.dto.receiver;

import org.hibernate.validator.constraints.br.CPF;

import com.informatica.controle_material.domain.model.Receiver;

import jakarta.validation.constraints.NotBlank;

public record AddReceiverDTO(
  @NotBlank(message = "O nome do militar deve ser informado")
  String name, 
  @NotBlank(message = "O nome de guerra do militar deve ser informado")
  String warName, 
  @NotBlank(message = "O posto/graduação do militar deve ser informado")
  String rank, 
  @NotBlank(message = "O companhia do militar deve ser informada")
  String company, 
  @NotBlank(message = "O CPF do militar deve ser informado")
  @CPF
  String cpf, 
  @NotBlank(message = "O telefone do militar deve ser informado")
  String telephone
) {
  
  public Receiver toModel() {
    Receiver receiver = new Receiver();
    receiver.setCompany(company);
    receiver.setCpf(cpf);
    receiver.setName(name);
    receiver.setTelephone(telephone);
    receiver.setWarName(warName);
    receiver.setRank(rank);

    return receiver;
  }

}
