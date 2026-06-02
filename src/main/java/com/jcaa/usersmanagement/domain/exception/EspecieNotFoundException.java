package com.jcaa.usersmanagement.domain.exception;

public final class EspecieNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID = "La Id de la especie '%s' no fue encontrada.";

  private EspecieNotFoundException(final String message) {
    super(message);
  }

  public static EspecieNotFoundException becauseIdWasNotFound(final String id) {
    return new EspecieNotFoundException(String.format(MESSAGE_BY_ID, id));
  }
}
