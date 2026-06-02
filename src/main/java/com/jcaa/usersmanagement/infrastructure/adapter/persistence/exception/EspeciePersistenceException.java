package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;


public final class EspeciePersistenceException extends RuntimeException {

  private EspeciePersistenceException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static EspeciePersistenceException becauseSaveFailed(
      final String id, final Throwable cause) {
    return new EspeciePersistenceException(
        String.format("Failed to save especie with id '%s'.", id), cause);
  }

  public static EspeciePersistenceException becauseUpdateFailed(
      final String id, final Throwable cause) {
    return new EspeciePersistenceException(
        String.format("Failed to update especie with id '%s'.", id), cause);
  }

  public static EspeciePersistenceException becauseFindByIdFailed(
      final String id, final Throwable cause) {
    return new EspeciePersistenceException(
        String.format("Failed to find especie with id '%s'.", id), cause);
  }

  public static EspeciePersistenceException becauseFindByNombreCientificoFailed(
      final String nombre, final Throwable cause) {
    return new EspeciePersistenceException(
        String.format("Failed to find especie with nombre cientifico '%s'.", nombre), cause);
  }

  public static EspeciePersistenceException becauseFindAllFailed(final Throwable cause) {
    return new EspeciePersistenceException("Failed to list all especies.", cause);
  }

  public static EspeciePersistenceException becauseDeleteFailed(
      final String id, final Throwable cause) {
    return new EspeciePersistenceException(
        String.format("Failed to delete especie with id '%s'.", id), cause);
  }
}
