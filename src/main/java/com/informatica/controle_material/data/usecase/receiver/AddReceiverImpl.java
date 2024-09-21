package com.informatica.controle_material.data.usecase.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.informatica.controle_material.data.dto.receiver.AddReceiverDTO;
import com.informatica.controle_material.domain.model.Receiver;
import com.informatica.controle_material.domain.usecases.receiver.AddReceiverUseCase;
import com.informatica.controle_material.infra.repository.ReceiverRepository;

@Service
public class AddReceiverImpl implements AddReceiverUseCase {
  
  @Autowired
  private ReceiverRepository receiverRepository;

  @Override
  public Receiver execute(AddReceiverDTO addReceiverDTO) {
    Receiver receiver = addReceiverDTO.toModel();
    
    return receiverRepository.save(receiver);
  }

}
