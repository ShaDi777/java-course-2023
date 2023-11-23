package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PiCalculatorTest {
    @Test
    void getApproximatePiSingleThread() {
        assertEquals(Math.PI, PiCalculator.singleThread(10000), 0.3);
    }

    @Test
    void getApproximatePiMultiThread() {
        assertEquals(Math.PI, PiCalculator.multiThread(100000, Runtime.getRuntime().availableProcessors()), 0.3);
    }
}
