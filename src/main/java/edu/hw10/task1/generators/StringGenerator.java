package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.NotNull;
import java.lang.reflect.Parameter;
import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator extends Generator {

    @Override
    public Object tryGenerateValue(Parameter parameter) {
        if (!parameter.getType().equals(String.class)) {
            return generateNext(parameter);
        }

        for (var annotation : parameter.getAnnotations()) {
            if (annotation instanceof NotNull) {
                return "NotNullString";
            }
        }

        return ThreadLocalRandom.current().nextBoolean()
            ? "RandomString"
            : null;
    }
}
