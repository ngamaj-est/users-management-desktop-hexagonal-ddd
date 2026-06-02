package com.jcaa.usersmanagement.domain.valueobject;

import java.util.Objects;

import com.jcaa.usersmanagement.domain.exception.InvalidNombreEspecieException;

public record NombreEspecie(String value) {
      private static final int MINIMUM_LENGTH = 2;

  public NombreEspecie {
    final String normalizedValue =
        Objects.requireNonNull(value, "NombreEspecie cannot be null").trim();
    validateNotEmpty(normalizedValue);
    validateMinimumLength(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String v) {
    if (v.isEmpty()) {
      throw InvalidNombreEspecieException.becauseValueIsEmpty();
    }
  }

  private static void validateMinimumLength(final String v) {
    if (v.length() < MINIMUM_LENGTH) {
      throw InvalidNombreEspecieException.becauseLengthIsTooShort(MINIMUM_LENGTH);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
