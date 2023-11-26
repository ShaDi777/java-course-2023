package edu.hw8;

import edu.hw8.Task3.PasswordSolver;
import edu.hw8.Task3.Profile;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordSolverTest {
    private static final Map<String, String> expectedResult = Map.of(
        "a.v.petrov", "123",
        "v.v.belov",  "1234"
    );

    private static final PasswordSolver solver = new PasswordSolver(
        List.of(
            new Profile("a.v.petrov", "202cb962ac59075b964b07152d234b70"),
            new Profile("v.v.belov", "81dc9bdb52d04dc20036dbd8313ed055")
        )
    );

    @Test
    void runSingleThread() {
        PasswordSolver testSolver = new PasswordSolver(solver);
        Map<String, String> solved = testSolver.solveSingleThread();
        assertThat(solved).isEqualTo(expectedResult);
    }

    @Test
    void runMultiThread() {
        PasswordSolver testSolver = new PasswordSolver(solver);
        Map<String, String> solved = testSolver.solveMultiThread(Runtime.getRuntime().availableProcessors());
        assertThat(solved).isEqualTo(expectedResult);
    }
}
