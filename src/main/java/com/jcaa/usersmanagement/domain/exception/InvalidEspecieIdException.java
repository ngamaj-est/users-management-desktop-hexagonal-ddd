package com.jcaa.usersmanagement.domain.exception;

public final class InvalidEspecieIdException extends DomainException {
      private InvalidEspecieIdException(final String message) {
    super(message);
  }

    public static InvalidEspecieIdException becauseValueIsEmpty() {
    return new InvalidEspecieIdException("EspecieId must not be empty.");
  }
}
