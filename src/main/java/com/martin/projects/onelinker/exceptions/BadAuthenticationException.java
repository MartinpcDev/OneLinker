package com.martin.projects.onelinker.exceptions;

public class BadAuthenticationException extends RuntimeException {

  public BadAuthenticationException(String message) {
    super(message);
  }
}
