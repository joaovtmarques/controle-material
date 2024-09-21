package com.informatica.controle_material.data.dto.admin;

import java.util.List;

import com.informatica.controle_material.domain.model.Role;
import com.informatica.controle_material.domain.model.User;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;

public record AddUserDTO(
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
  String telephone,
  @NotBlank(message = "O email do usuário deve ser informado.")
  String email,
  @NotBlank(message = "A senha do usuário deve ser informada.")
  String password,
  @NotBlank(message = "A tipo de usuário deve ser informado.")
  String type,
  List<Role> roles
) {
  
  public User toModel(String encodedPassword) {
    User user = new User();
    user.setCompany(company);
    user.setCpf(cpf);
    user.setName(name);
    user.setTelephone(telephone);
    user.setWarName(warName);
    user.setRank(rank);
    user.setEmail(email);
    user.setPassword(encodedPassword);
    user.setType(type);
    user.setRoles(roles);
    
    return user;
  }

}
