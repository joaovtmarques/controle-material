package com.informatica.controle_material.presentation.controller.protocol;

public interface ControllerProtocol<B, R> {
  
  R handle(B b);
  
}

