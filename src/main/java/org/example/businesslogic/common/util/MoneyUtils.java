package org.example.businesslogic.common.util;

public final class MoneyUtils {

    private MoneyUtils() {
    }

    // keep only 2 numbers after the decimal point
    public static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
