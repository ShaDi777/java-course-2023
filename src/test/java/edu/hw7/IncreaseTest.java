package edu.hw7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class IncreaseTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 10, 100, 10000})
    void countUntil(int n) throws InterruptedException {
        ThreadIncreaser increaser = new ThreadIncreaser();
        increaser.incrementUntil(n);
        assertThat(increaser.getCounter()).isEqualTo(n);
    }
}
