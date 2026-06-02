package com.jcaa.usersmanagement.domain.exception;

public final class InvalidNombreCientificoException extends DomainException {

  private InvalidNombreCientificoException(final String message) {
    super(message);
  }

  public static InvalidNombreCientificoException becauseValueIsEmpty() {
    return new InvalidNombreCientificoException("NombreCientifico no puede estar vacío.");
  }
  public static InvalidNombreCientificoException becauseLengthIsTooShort(final int min) {
    return new InvalidNombreCientificoException(
        String.format("NombreCientifico debe tener al menos %d caracteres.", min));
  }
}
