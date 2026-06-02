package com.jcaa.usersmanagement.domain.valueobject;

import java.util.Objects;

import com.jcaa.usersmanagement.domain.exception.InvalidNombreCientificoException;

public record NombreCientifico(String value) {
    private static final int MINIMUM_LENGTH = 3;

  public NombreCientifico {
    final String normalizedValue =
        Objects.requireNonNull(value, "NombreCientifico cannot be null").trim();
    validateNotEmpty(normalizedValue);
    validateMinimumLength(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String v) {
    if (v.isEmpty()) {
      throw InvalidNombreCientificoException.becauseValueIsEmpty();
    }
  }

  private static void validateMinimumLength(final String v) {
    if (v.length() < MINIMUM_LENGTH) {
      throw InvalidNombreCientificoException.becauseLengthIsTooShort(MINIMUM_LENGTH);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
