package com.martin.projects.onelinker.exceptions;

public class NoConfirmatedPasswordException extends RuntimeException {

  public NoConfirmatedPasswordException(String message) {
    super(message);
  }
}
