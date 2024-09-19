package com.informatica.controle_material.data.exception;

public class BadRequestException extends RuntimeException {

  public BadRequestException() {
    super("Bad request");
  }

  public BadRequestException(String message) {
    super(message);
  }

}
