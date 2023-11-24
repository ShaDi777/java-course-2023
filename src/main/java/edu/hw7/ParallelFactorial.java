package edu.hw7;

import java.util.stream.LongStream;

public final class ParallelFactorial {
    private ParallelFactorial() {
    }

    public static long getFactorial(long n) {
        return LongStream.rangeClosed(1, n)
            .parallel()
            .reduce(1, (agg, val) -> agg * val);
    }
}
