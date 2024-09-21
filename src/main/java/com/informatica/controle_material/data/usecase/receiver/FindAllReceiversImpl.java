package com.informatica.controle_material.data.usecase.receiver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.data.exception.BadRequestException;
import com.informatica.controle_material.domain.model.Receiver;
import com.informatica.controle_material.domain.usecases.receiver.FindAllReceiversUseCase;
import com.informatica.controle_material.infra.repository.ReceiverRepository;

@Service
public class FindAllReceiversImpl implements FindAllReceiversUseCase {
  
  @Autowired
  private ReceiverRepository receiverRepository;

  @Override
  public List<Receiver> execute() {
    try {
      return receiverRepository.findAll();
    } catch (Exception e) {
      throw new BadRequestException("Erro ao buscar os recebedores");
    }
  }

}
