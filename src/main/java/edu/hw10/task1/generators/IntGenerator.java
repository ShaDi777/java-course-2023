package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class IntGenerator extends Generator {
    @SuppressWarnings("checkstyle:InnerAssignment")
    @Override
    public Object tryGenerateValue(Parameter parameter) {
        if (!(parameter.getType().equals(int.class) || parameter.getType().equals(Integer.class))) {
            return generateNext(parameter);
        }

        var minValue = Integer.MIN_VALUE;
        var maxValue = Integer.MAX_VALUE;
        for (var annotation : parameter.getAnnotations()) {
            switch (annotation) {
                case Min m -> minValue = Integer.parseInt(m.value());
                case Max m -> maxValue = Integer.parseInt(m.value());
                default -> {
                }
            }
        }

        return ThreadLocalRandom.current().nextInt(minValue, maxValue);
    }
}
