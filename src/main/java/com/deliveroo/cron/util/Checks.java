package com.deliveroo.cron.util;

public final class Checks {

    public static void checkThat(boolean condition, String errorMessage) {
        if (!condition)
            throw new IllegalArgumentException(errorMessage);
    }

    private Checks() {
        throw new AssertionError("No com.deliveroo.cron.util.Checks instances for you!");
    }
}