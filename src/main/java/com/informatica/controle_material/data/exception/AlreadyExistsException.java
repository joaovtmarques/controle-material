package com.informatica.controle_material.data.exception;

public class AlreadyExistsException extends RuntimeException {
  
  public AlreadyExistsException() {
    super("Um item com estes dados já está cadastrado");
  }

  public AlreadyExistsException(String message) {
    super(message);
  }

}
