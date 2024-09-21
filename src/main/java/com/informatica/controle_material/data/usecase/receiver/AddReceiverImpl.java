package com.informatica.controle_material.data.usecase.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatica.controle_material.data.dto.receiver.AddReceiverDTO;
import com.informatica.controle_material.data.exception.AlreadyExistsException;
import com.informatica.controle_material.domain.model.Receiver;
import com.informatica.controle_material.domain.usecases.receiver.AddReceiverUseCase;
import com.informatica.controle_material.infra.repository.ReceiverRepository;

@Service
public class AddReceiverImpl implements AddReceiverUseCase {
  
  @Autowired
  private ReceiverRepository receiverRepository;

  @Transactional
  @Override
  public Receiver execute(AddReceiverDTO addReceiverDTO) {
    if (receiverRepository.findByCpf(addReceiverDTO.cpf()).isPresent()) {
      throw new AlreadyExistsException("O CPF informado já está cadastrado");
    }
  
    Receiver receiver = addReceiverDTO.toModel();
    
    return receiverRepository.save(receiver);
  }

}
