package edu.hw8;

import edu.hw8.Task3.PasswordSolver;
import edu.hw8.Task3.Profile;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(value = 1, warmups = 0)
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 5)
public class PasswordSolverBenchmark {
    public List<Profile> profiles = List.of(
        new Profile("userA", "d88c146dfafdea37a837778a92415bc2"), // A0
        new Profile("userB", "900150983cd24fb0d6963f7d28e17f72"), // abc
        new Profile("userC", "202cb962ac59075b964b07152d234b70"), // 123
        new Profile("userD", "3e0fd1ad8efb39d90b8cd3b04a6c94f1"), // r2d2
        new Profile("userE", "912e79cd13c64069d91da65d62fbb78c")  // 9876
    );

    @Benchmark
    public void singleThread(Blackhole blackhole) {
        PasswordSolver solver = new PasswordSolver(profiles);
        blackhole.consume(solver.solveSingleThread());
    }

    @Benchmark
    public void twoThread(Blackhole blackhole) {
        PasswordSolver solver = new PasswordSolver(profiles);
        blackhole.consume(solver.solveMultiThread(2));
    }

    @Benchmark
    public void fourThread(Blackhole blackhole) {
        PasswordSolver solver = new PasswordSolver(profiles);
        blackhole.consume(solver.solveMultiThread(4));
    }

    @Benchmark
    public void eightThread(Blackhole blackhole) {
        PasswordSolver solver = new PasswordSolver(profiles);
        blackhole.consume(solver.solveMultiThread(8));
    }

    @Benchmark
    public void sixteenThread(Blackhole blackhole) {
        PasswordSolver solver = new PasswordSolver(profiles);
        blackhole.consume(solver.solveMultiThread(16));
    }

    @Benchmark
    public void maxThread(Blackhole blackhole) {
        PasswordSolver solver = new PasswordSolver(profiles);
        blackhole.consume(solver.solveMultiThread(Runtime.getRuntime().availableProcessors()));
    }
    /*
    Local results:
    Benchmark                              Mode  Cnt  Score   Error  Units
    PasswordSolverBenchmark.singleThread   avgt       4,473           s/op
    PasswordSolverBenchmark.twoThread      avgt       4,652           s/op
    PasswordSolverBenchmark.fourThread     avgt       3,605           s/op
    PasswordSolverBenchmark.eightThread    avgt       2,663           s/op
    PasswordSolverBenchmark.sixteenThread  avgt       1,754           s/op
    PasswordSolverBenchmark.maxThread      avgt       1,702           s/op
    */
}
