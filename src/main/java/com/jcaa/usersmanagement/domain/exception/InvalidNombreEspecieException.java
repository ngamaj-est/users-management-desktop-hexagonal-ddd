package com.jcaa.usersmanagement.domain.exception;

public final class InvalidNombreEspecieException extends DomainException {

  private InvalidNombreEspecieException(final String message) {
    super(message);
  }

  public static InvalidNombreEspecieException becauseValueIsEmpty() {
    return new InvalidNombreEspecieException("NombreEspecie no puede estar vacío.");
  }

  public static InvalidNombreEspecieException becauseLengthIsTooShort(final int min) {
    return new InvalidNombreEspecieException(
        String.format("NombreEspecie debe tener al menos %d caracteres.", min));
  }
}
