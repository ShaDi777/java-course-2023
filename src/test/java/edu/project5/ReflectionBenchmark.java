package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

/*
Benchmark                              Mode  Cnt  Score   Error  Units
ReflectionBenchmark.directAccess       avgt       0,388          ns/op
ReflectionBenchmark.lambdaMetafactory  avgt       0,523          ns/op
ReflectionBenchmark.methodHandles      avgt       2,127          ns/op
ReflectionBenchmark.reflection         avgt       4,249          ns/op
*/

@State(Scope.Thread)
public class ReflectionBenchmark {
    private static final String STUDENT_NAME = "Dmitry";
    private static final String STUDENT_SURNAME = "Shalanov";
    private static final String METHOD_NAME = "name";
    private static final String METAFACTORY_METHOD_NAME = "apply";

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(5))
            .build();

        new Runner(options).run();
    }

    record Student(String name, String surname) {
    }

    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Function<Student, String> lambdaFunction;

    @Setup
    public void setup() throws Throwable {
        student = new Student(STUDENT_NAME, STUDENT_SURNAME);
        method = Student.class.getMethod(METHOD_NAME);

        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, METHOD_NAME, MethodType.methodType(String.class));
        CallSite site = LambdaMetafactory.metafactory(
            lookup,
            METAFACTORY_METHOD_NAME,
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class), // Function::apply signature
            methodHandle,
            methodHandle.type() // Person::getName signature
        );
        lambdaFunction = (Function<Student, String>) site.getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        String name = (String) method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandles(Blackhole bh) throws Throwable {
        String name = (String) methodHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) {
        String name = lambdaFunction.apply(student);
        bh.consume(name);
    }
}
