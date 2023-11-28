package edu.hw8;

import edu.hw8.Task3.PasswordSolver;
import edu.hw8.Task3.Profile;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordSolverTest {
    private static final Map<String, String> expectedResult = Map.of(
        "userA", "A0",
        "userB", "abc",
        "userC", "123",
        "userD", "r2d2",
        "userE", "9876"
    );

    public static List<Profile> profiles = List.of(
        new Profile("userA", "d88c146dfafdea37a837778a92415bc2"), // A0
        new Profile("userB", "900150983cd24fb0d6963f7d28e17f72"), // abc
        new Profile("userC", "202cb962ac59075b964b07152d234b70"), // 123
        new Profile("userD", "3e0fd1ad8efb39d90b8cd3b04a6c94f1"), // r2d2
        new Profile("userE", "912e79cd13c64069d91da65d62fbb78c")  // 9876
    );

    private static final PasswordSolver solver = new PasswordSolver(profiles);

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
