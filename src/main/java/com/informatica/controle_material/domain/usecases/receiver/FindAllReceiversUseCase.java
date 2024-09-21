package com.informatica.controle_material.domain.usecases.receiver;

import java.util.List;

import com.informatica.controle_material.domain.model.Receiver;

public interface FindAllReceiversUseCase {
  
  List<Receiver> execute();

}
