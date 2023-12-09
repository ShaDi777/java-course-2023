package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class FloatGenerator extends Generator {
    @SuppressWarnings("checkstyle:InnerAssignment")
    @Override
    public Object tryGenerateValue(Parameter parameter) {
        if (!(parameter.getType().equals(float.class) || parameter.getType().equals(Float.class))) {
            return generateNext(parameter);
        }

        var minValue = Float.MIN_VALUE;
        var maxValue = Float.MAX_VALUE;
        for (var annotation : parameter.getAnnotations()) {
            switch (annotation) {
                case Min m -> minValue = Float.parseFloat(m.value());
                case Max m -> maxValue = Float.parseFloat(m.value());
                default -> {
                }
            }
        }

        return ThreadLocalRandom.current().nextFloat(minValue, maxValue);
    }
}
