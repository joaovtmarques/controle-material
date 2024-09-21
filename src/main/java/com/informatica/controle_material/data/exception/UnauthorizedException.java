package com.informatica.controle_material.data.exception;

public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException() {
    super("Não autorizado");
  }

  public UnauthorizedException(String message) {
    super(message);
  }
  
}
