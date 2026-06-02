package com.jcaa.usersmanagement.domain.exception;

public final class EspecieAlreadyExistsException extends DomainException {

  private static final String MESSAGE =
      "La especie con el nombre cientifico '%s' ya existe en el sistema.";

  private EspecieAlreadyExistsException(final String message) {
    super(message);
  }

  public static EspecieAlreadyExistsException becauseNombreCientificoAlreadyExists(
      final String nombreCientifico) {
    return new EspecieAlreadyExistsException(
        String.format(MESSAGE, nombreCientifico));
  }
}