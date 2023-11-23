package edu.hw8;

import edu.hw8.Task3.PasswordSolver;
import edu.hw8.Task3.Profile;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PasswordSolverTest {
    @Test
    void runSingleThread() {
        Map<String, String> hackedProfiles = Map.of(
            "a.v.petrov", "123"
           //"v.v.belov",  "1234"
           //"a.s.ivanov", "12345"
           // "k.p.maslov", "password"
        );

        var solver = new PasswordSolver(
            List.of(
                new Profile("a.v.petrov", "202cb962ac59075b964b07152d234b70")
                //new Profile("v.v.belov", "81dc9bdb52d04dc20036dbd8313ed055"),
                //new Profile("a.s.ivanov", "827ccb0eea8a706c4c34a16891f84e7b")
                //new Profile("k.p.maslov", "5f4dcc3b5aa765d61d8327deb882cf99")
            )
        );

        // Map<String, String> solved = solver.solveMultiThread(Runtime.getRuntime().availableProcessors()); // 3:39
        Map<String, String> solved = solver.solveSingleThread(); // 3:36
        assertThat(solved).isEqualTo(hackedProfiles);
    }
}
