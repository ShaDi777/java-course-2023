package edu.hw9;

import edu.hw9.task1.Stats;
import edu.hw9.task1.StatsCollector;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsCollectorTests {
    private static Stream<Arguments> getRandomArrays() {
        return Stream.of(
            Arguments.of(
                List.of(
                    new double[] { 1, 3 },
                    new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                    new double[] { 0, 10, -5, -5, 0}
                ),
                Map.of(
                    "Name 0", new Stats(4, 2, 1, 3),
                    "Name 1", new Stats(45, 5, 1, 9),
                    "Name 2", new Stats(0, 0, -5, 10)
                )
            ));
    }

    @ParameterizedTest
    @MethodSource("getRandomArrays")
    public void statsCollector_shouldProcessAllSentMetrics(List<double[]> values, Map<String, Stats> expected)
        throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        StatsCollector collector = new StatsCollector(5);
        for (int i = 0; i < values.size(); i++) {
            final int copy = i;
            executorService.execute(() -> collector.push("Name " + copy, values.get(copy)));
        }
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        assertThat(collector.getStats()).containsExactlyInAnyOrderEntriesOf(expected);
    }
}
