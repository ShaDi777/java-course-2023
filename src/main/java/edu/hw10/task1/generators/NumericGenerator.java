package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NumericGenerator extends Generator {
    private static final List<Class<?>> PRIMITIVE_CLASSES = List.of(
        byte.class,
        short.class,
        int.class,
        long.class,
        float.class,
        double.class
    );

    private enum NumericType {
        BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE
    }

    @SuppressWarnings("checkstyle:InnerAssignment")
    @Override
    public Object tryGenerateValue(Parameter parameter) {
        var type = parameter.getType();
        if (!(PRIMITIVE_CLASSES.contains(type) || Number.class.isAssignableFrom(type))) {
            return generateNext(parameter);
        }

        double minValue = Integer.MIN_VALUE;
        double maxValue = Integer.MAX_VALUE;
        for (var annotation : parameter.getAnnotations()) {
            switch (annotation) {
                case Min m -> minValue = Double.parseDouble(m.value());
                case Max m -> maxValue = Double.parseDouble(m.value());
                default -> {
                }
            }
        }

        var num = (Number) ThreadLocalRandom.current().nextDouble(minValue, maxValue);

        return switch (getType(type)) {
            case BYTE -> num.byteValue();
            case SHORT -> num.shortValue();
            case INTEGER -> num.intValue();
            case LONG -> num.longValue();
            case FLOAT -> num.floatValue();
            case DOUBLE -> num.doubleValue();
        };
    }

    private NumericType getType(Class<?> type) {
        var numericType = NumericType.DOUBLE;

        if (type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)) {
            numericType = NumericType.INTEGER;
        } else if (type.isAssignableFrom(long.class) || type.isAssignableFrom(Long.class)) {
            numericType = NumericType.LONG;
        } else if (type.isAssignableFrom(byte.class) || type.isAssignableFrom(Byte.class)) {
            numericType = NumericType.BYTE;
        } else if (type.isAssignableFrom(short.class) || type.isAssignableFrom(Short.class)) {
            numericType = NumericType.SHORT;
        } else if (type.isAssignableFrom(float.class) || type.isAssignableFrom(Float.class)) {
            numericType = NumericType.FLOAT;
        }

        return numericType;
    }
}
