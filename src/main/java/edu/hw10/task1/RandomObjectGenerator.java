package edu.hw10.task1;

import edu.hw10.task1.generators.DoubleGenerator;
import edu.hw10.task1.generators.FloatGenerator;
import edu.hw10.task1.generators.Generator;
import edu.hw10.task1.generators.IntGenerator;
import edu.hw10.task1.generators.LongGenerator;
import edu.hw10.task1.generators.StringGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;

public class RandomObjectGenerator {
    private final Generator generatorChain;

    public RandomObjectGenerator() {
        this.generatorChain = Generator.chain(
            new IntGenerator(),
            new LongGenerator(),
            new FloatGenerator(),
            new DoubleGenerator(),
            new StringGenerator()
        );
    }

    public RandomObjectGenerator(Generator chain) {
        this.generatorChain = chain;
    }

    public <T> T nextObject(Class<T> targetClass)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        var constructor = Arrays.stream(targetClass.getDeclaredConstructors())
            .max(Comparator.comparingInt(Constructor::getParameterCount))
            .orElseThrow();
        constructor.setAccessible(true);
        return (T) constructor.newInstance(getArgs(constructor));
    }

    public <T> T nextObject(Class<T> targetClass, String creationMethodName)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        if (creationMethodName == null) {
            return nextObject(targetClass);
        }

        Method createMethod = Arrays.stream(targetClass.getDeclaredMethods())
            .filter(method -> method.getName().equals(creationMethodName))
            .findFirst()
            .orElseThrow();

        createMethod.setAccessible(true);
        return (T) createMethod.invoke(null, getArgs(createMethod));
    }

    private Object[] getArgs(Executable method) {
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            args[i] = generatorChain.tryGenerateValue(parameters[i]);
        }

        return args;
    }
}
