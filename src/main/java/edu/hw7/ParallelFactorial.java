package edu.hw7;

import java.util.stream.LongStream;

public final class ParallelFactorial {
    private ParallelFactorial() {
    }

    // Задание 2
    // Реализуйте функцию, которая вычисляет факториал числа
    // в многопоточном режиме при помощи parallelStream.
    public static long getFactorial(long n) {
        return LongStream.rangeClosed(1, n)
            // .mapToObj(BigInteger::valueOf)
            .parallel()
            .reduce(1, (agg, val) -> agg * val);
    }
}
