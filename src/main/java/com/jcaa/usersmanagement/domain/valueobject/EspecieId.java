package com.jcaa.usersmanagement.domain.valueobject;
import java.util.Objects;

import com.jcaa.usersmanagement.domain.exception.InvalidEspecieIdException;

public record EspecieId(String value) {

  public EspecieId {
    final String normalizedValue =
        Objects.requireNonNull(value, "EspecieId cannot be null").trim();
    validateNotEmpty(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidEspecieIdException.becauseValueIsEmpty();
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
