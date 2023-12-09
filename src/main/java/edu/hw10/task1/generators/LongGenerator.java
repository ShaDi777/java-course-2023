package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class LongGenerator extends Generator {
    @SuppressWarnings("checkstyle:InnerAssignment")
    @Override
    public Object tryGenerateValue(Parameter parameter) {
        if (!(parameter.getType().equals(long.class) || parameter.getType().equals(Long.class))) {
            return generateNext(parameter);
        }

        var minValue = Long.MIN_VALUE;
        var maxValue = Long.MAX_VALUE;
        for (var annotation : parameter.getAnnotations()) {
            switch (annotation) {
                case Min m -> minValue = Long.parseLong(m.value());
                case Max m -> maxValue = Long.parseLong(m.value());
                default -> {
                }
            }
        }

        return ThreadLocalRandom.current().nextLong(minValue, maxValue);
    }
}
