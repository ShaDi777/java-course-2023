package edu.hw8;

import edu.hw8.Task2.FixedThreadPool;
import edu.hw8.Task2.ThreadPool;
import java.util.concurrent.CountDownLatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ThreadPoolTest {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void fibonacciThreadPool() {
        int maxI = 10;
        long[] fibonacci = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
        CountDownLatch latch = new CountDownLatch(10);

        try (ThreadPool threadPool = FixedThreadPool.create(5)) {
            threadPool.start();

            for (int i = 1; i <= maxI; i++) {
                final int finalI = i;
                threadPool.execute(() -> {
                    long result = calculateFibonacci(finalI);
                    LOGGER.trace(result);
                    assertThat(result).isEqualTo(fibonacci[finalI]);
                    latch.countDown();
                });
            }

            latch.await();
        } catch (Exception e) {
            LogManager.getLogger().error(e.getMessage());
        }
    }

    private long calculateFibonacci(long n) {
        if (n <= 1) {
            return n;
        } else {
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }
}
