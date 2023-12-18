package edu.hw10.models.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public final class ClassWithStaticCreate {
    private final String stringValue;
    private final int intValue;
    private final double doubleValue;
    private final float floatValue;

    private ClassWithStaticCreate(
        String stringValue,
        int intValue,
        double doubleValue,
        float floatValue
    ) {
        this.stringValue = stringValue;
        this.intValue = intValue;
        this.doubleValue = doubleValue;
        this.floatValue = floatValue;
    }

    public static ClassWithStaticCreate create(
        @NotNull String stringValue,
        @Min("10") int intValue,
        @Max("3.1415") double doubleValue,
        @Min("0.5") @Max("2.5") float floatValue
    ) {
        return new ClassWithStaticCreate(stringValue, intValue, doubleValue, floatValue);
    }

    public String getStringValue() {
        return stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public float getFloatValue() {
        return floatValue;
    }
}
