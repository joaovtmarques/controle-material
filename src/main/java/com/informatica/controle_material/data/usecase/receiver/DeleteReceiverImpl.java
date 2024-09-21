package com.informatica.controle_material.data.usecase.receiver;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_material.data.exception.NotFoundException;
import com.informatica.controle_material.domain.model.Receiver;
import com.informatica.controle_material.domain.usecases.receiver.DeleteReceiverUseCase;
import com.informatica.controle_material.infra.repository.ReceiverRepository;

@Service
public class DeleteReceiverImpl implements DeleteReceiverUseCase {
  
  @Autowired
  private ReceiverRepository receiverRepository;

  @Transactional
  @Override
  public void execute(Long id) {
    Optional<Receiver> receiverExists = receiverRepository.findById(id);

    if(receiverExists.isEmpty()) {
      throw new NotFoundException("O recebedor "+id+" não existe");
    }

    receiverRepository.deleteById(id);
  }

}
