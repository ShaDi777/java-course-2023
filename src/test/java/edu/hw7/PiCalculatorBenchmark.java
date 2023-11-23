package edu.hw7;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(value = 1, warmups = 0)
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 10)
public class PiCalculatorBenchmark {
    @Param({"10000", "100000", "1000000", "10000000"})
    public int totalDots;

    @Benchmark
    public void singleThread(Blackhole blackhole) {
        blackhole.consume(PiCalculator.singleThread(totalDots));
    }

    @Benchmark
    public void multiThread(Blackhole blackhole) {
        blackhole.consume(PiCalculator.multiThread(totalDots, Runtime.getRuntime().availableProcessors()));
    }
    /*
    Local results:
    Benchmark                           (totalDots)   Mode  Cnt     Score   Error  Units
    PiCalculatorBenchmark.multiThread         10000  thrpt       1055,295          ops/s
    PiCalculatorBenchmark.multiThread        100000  thrpt        795,748          ops/s
    PiCalculatorBenchmark.multiThread       1000000  thrpt        255,053          ops/s
    PiCalculatorBenchmark.multiThread      10000000  thrpt         34,341          ops/s
    PiCalculatorBenchmark.singleThread        10000  thrpt       2778,529          ops/s
    PiCalculatorBenchmark.singleThread       100000  thrpt        278,502          ops/s
    PiCalculatorBenchmark.singleThread      1000000  thrpt         28,051          ops/s
    PiCalculatorBenchmark.singleThread     10000000  thrpt          2,812          ops/s
    */
}
