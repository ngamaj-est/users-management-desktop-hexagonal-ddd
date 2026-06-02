package com.jcaa.usersmanagement.domain.enums;

import java.util.Objects;

public enum HabitatEnum {
    SABANA,
    DESIERTO,
    BOSQUE,
    SELVA;

    public static HabitatEnum fromString(final String value) {
        final String normalizedValue =
            Objects.requireNonNull(value, "Habitat cannot be null").trim();

        for (final HabitatEnum habitat : values()) {
            if (habitat.name().equalsIgnoreCase(normalizedValue)) {
                return habitat;
            }
        }

        throw new IllegalArgumentException(
            String.format(
                "The habitat '%s' is not valid. Valid values are: %s.",
                value,
                allowedValues()));
    }

    public static String allowedValues() {
        final StringBuilder builder = new StringBuilder();
        for (final HabitatEnum habitat : values()) {
            if (builder.length() > 0) {
                builder.append(", ");
            }
            builder.append(habitat.name());
        }
        return builder.toString();
    }
}
