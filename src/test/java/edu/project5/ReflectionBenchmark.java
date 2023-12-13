package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaConversionException;
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
Benchmark                              Mode  Cnt   Score   Error  Units
ReflectionBenchmark.directAccess       avgt       11,508          ns/op
ReflectionBenchmark.lambdaMetafactory  avgt       13,835          ns/op
ReflectionBenchmark.methodHandles      avgt       12,442          ns/op
ReflectionBenchmark.reflection         avgt       14,245          ns/op
*/

@State(Scope.Thread)
public class ReflectionBenchmark {
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
        public String prettyString() {
            return "Student: " + name + " " + surname;
        }
    }

    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private CallSite site;

    @Setup
    public void setup()
        throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException, LambdaConversionException {
        student = new Student("Alexander", "Biryukov");
        method = Student.class.getMethod("prettyString");

        final MethodHandles.Lookup lookup = MethodHandles.lookup();
        methodHandle = lookup.findVirtual(Student.class, "prettyString", MethodType.methodType(String.class));
        site = LambdaMetafactory.metafactory(
            lookup,
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class), // Function::apply signature
            methodHandle,
            methodHandle.type() // Person::getName signature
        );
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.prettyString();
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
    public void lambdaMetafactory(Blackhole bh) throws Throwable {
        Function<Student, String> function = (Function<Student, String>) site.getTarget().invokeExact();
        String name = function.apply(student);
        bh.consume(name);
    }
}
