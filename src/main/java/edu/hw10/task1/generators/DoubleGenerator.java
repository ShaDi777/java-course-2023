package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class DoubleGenerator extends Generator {
    @SuppressWarnings("checkstyle:InnerAssignment")
    @Override
    public Object tryGenerateValue(Parameter parameter) {
        if (!(parameter.getType().equals(double.class) || parameter.getType().equals(Double.class))) {
            return generateNext(parameter);
        }

        var minValue = Double.MIN_VALUE;
        var maxValue = Double.MAX_VALUE;
        for (var annotation : parameter.getAnnotations()) {
            switch (annotation) {
                case Min m -> minValue = Double.parseDouble(m.value());
                case Max m -> maxValue = Double.parseDouble(m.value());
                default -> {
                }
            }
        }

        return ThreadLocalRandom.current().nextDouble(minValue, maxValue);
    }
}
