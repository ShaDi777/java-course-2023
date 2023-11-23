package edu.hw7;

import java.text.DecimalFormat;
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

    /*
    LOCAL TESTS:

    10_000 total dots:
    With 1 threads. Relative error = 0,17%. Time = 2256960 nanoseconds.
    With 2 threads. Relative error = 0,11%. Time = 2192350 nanoseconds. 1,03 times faster.
    With 3 threads. Relative error = 0,07%. Time = 1805220 nanoseconds. 1,25 times faster. BEST
    With 4 threads. Relative error = 0,07%. Time = 1992160 nanoseconds. 1,13 times faster.
    With 5 threads. Relative error = 0,07%. Time = 1896920 nanoseconds. 1,19 times faster.
    With 6 threads. Relative error = 0,05%. Time = 2549380 nanoseconds. 1,13 times slower.
    With 7 threads. Relative error = 0,02%. Time = 2953690 nanoseconds. 1,31 times slower.
    With 8 threads. Relative error = 0,04%. Time = 2844480 nanoseconds. 1,26 times slower.

    100_000 total dots:
    With 1 threads. Relative error = 0,02%. Time = 19556890 nanoseconds.
    With 2 threads. Relative error = 0,04%. Time = 11021190 nanoseconds. 1,77 times faster.
    With 3 threads. Relative error = 0,01%. Time = 7725090 nanoseconds. 2,53 times faster.
    With 4 threads. Relative error = 0,01%. Time = 6211540 nanoseconds. 3,15 times faster.
    With 5 threads. Relative error = 0,04%. Time = 5352600 nanoseconds. 3,65 times faster.
    With 6 threads. Relative error = 0,01%. Time = 5037830 nanoseconds. 3,88 times faster.
    With 7 threads. Relative error = 0,02%. Time = 5018640 nanoseconds. 3,9 times faster.
    With 8 threads. Relative error = 0,01%. Time = 4616140 nanoseconds. 4,24 times faster. BEST

    1_000_000 total dots:
    With 1 threads. Relative error = 0%. Time = 179254140 nanoseconds.
    With 2 threads. Relative error = 0%. Time = 89888240 nanoseconds. 1,99 times faster.
    With 3 threads. Relative error = 0,01%. Time = 61909910 nanoseconds. 2,9 times faster.
    With 4 threads. Relative error = 0%. Time = 49619280 nanoseconds. 3,61 times faster.
    With 5 threads. Relative error = 0%. Time = 40231680 nanoseconds. 4,46 times faster.
    With 6 threads. Relative error = 0,01%. Time = 34540830 nanoseconds. 5,19 times faster.
    With 7 threads. Relative error = 0%. Time = 30843590 nanoseconds. 5,81 times faster.
    With 8 threads. Relative error = 0%. Time = 27612790 nanoseconds. 6,49 times faster. BEST

    10_000_000 total dots:
    With 1 threads. Relative error = 0%. Time = 1714561570 nanoseconds.
    With 2 threads. Relative error = 0%. Time = 884824720 nanoseconds. 1,94 times faster.
    With 3 threads. Relative error = 0%. Time = 604346640 nanoseconds. 2,84 times faster.
    With 4 threads. Relative error = 0%. Time = 472475330 nanoseconds. 3,63 times faster.
    With 5 threads. Relative error = 0%. Time = 387782140 nanoseconds. 4,42 times faster.
    With 6 threads. Relative error = 0%. Time = 324050690 nanoseconds. 5,29 times faster.
    With 7 threads. Relative error = 0%. Time = 278213690 nanoseconds. 6,16 times faster.
    With 8 threads. Relative error = 0%. Time = 242738000 nanoseconds. 7,06 times faster. BEST
    */
    public static void main(String[] args) {
        // doFullComparison(10_000, 50);
        // doFullComparison(100_000, 50);
        // doFullComparison(1_000_000, 50);
        // doFullComparison(10_000_000, 50);
    }

    private static void doFullComparison(int totalDots, int simulationCount) {
        if (simulationCount <= 0 || totalDots < 0) {
            return;
        }

        long singleThreadTime = 0;
        System.out.println(totalDots + " total dots:");
        for (int threadCount = 1; threadCount <= 8; threadCount++) {
            long avgTime = 0;
            double avgPI = 0;
            for (int i = 0; i < simulationCount; i++) {
                long start = System.nanoTime();
                avgPI += threadCount == 1
                    ? PiCalculator.singleThread(totalDots)
                    : PiCalculator.multiThread(totalDots, threadCount);
                long finish = System.nanoTime();
                avgTime += finish - start;
            }
            avgPI /= simulationCount;
            avgTime /= 10;
            if (threadCount == 1) {
                singleThreadTime = avgTime;
            }

            DecimalFormat df = new DecimalFormat("###.##");
            System.out.println(
                "With " + threadCount + " threads. " +
                    "Relative error = " + df.format(100 * Math.abs(Math.PI - avgPI) / Math.PI) + "%. " +
                    "Time = " + avgTime + " nanoseconds. " +
                    (
                        (threadCount != 1)
                            ? (singleThreadTime > avgTime
                            ? df.format((double) singleThreadTime / avgTime) + " times faster."
                            : df.format((double) avgTime / singleThreadTime) + " times slower.")
                            : ""
                    )
            );
        }

    }
}
