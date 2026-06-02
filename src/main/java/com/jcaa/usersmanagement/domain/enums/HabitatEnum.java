package com.jcaa.usersmanagement.domain.enums;

public enum HabitatEnum {
    SABANA,
    DESIERTO,
    BOSQUE,
    SELVA;

    public static HabitatEnum fromString(final String value) {
        for (final HabitatEnum habitat : values()) {
            if (habitat.name().equalsIgnoreCase(value)) {
                return habitat;
            }
        }
        throw new IllegalArgumentException(String.format("The habitat '%s' is not valid.", value));
    }
}
