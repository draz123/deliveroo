package com.cron.util;

public final class Checks {

    public static void checkThat(boolean condition, String errorMessage) {
        if (!condition)
            throw new IllegalArgumentException(errorMessage);
    }

    private Checks() {
        throw new AssertionError("No com.cron.util.Checks instances for you!");
    }
}