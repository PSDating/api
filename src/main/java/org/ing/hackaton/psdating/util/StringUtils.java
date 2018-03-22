package org.ing.hackaton.psdating.util;

public final class StringUtils {
    private StringUtils() {}

    public static final String nonUpperCase(final String string) {
        if (string.equals(string.toUpperCase())) {
            return properCase(string);
        }
        return string;
    }

    public static final String properCase(final String string) {
        if (string.length() < 2) {
            return string;
        }
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }
}
