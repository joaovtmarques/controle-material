package com.informatica.controle_material.domain.usecases.receiver;

import com.informatica.controle_material.data.dto.receiver.AddReceiverDTO;
import com.informatica.controle_material.domain.model.Receiver;

public interface AddReceiverUseCase {
  
  Receiver execute(AddReceiverDTO addReceiverDTO);

}
