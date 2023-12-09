package edu.hw10.task1.generators;

import java.lang.reflect.Parameter;

public abstract class Generator {
    private Generator next;

    public static Generator chain(Generator first, Generator... chain) {
        Generator head = first;
        for (Generator nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract Object tryGenerateValue(Parameter parameter);

    protected Object generateNext(Parameter parameter) {
        if (next == null) {
            return null;
        }
        return next.tryGenerateValue(parameter);
    }
}
