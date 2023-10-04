package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task6 {
    private final static Logger LOGGER = LogManager.getLogger();

    private static final int KAPREKAR_CONST = 6174;
    private static final int KAPREKAR_BOTTOM_RANGE_LIMIT = 1000;
    private static final int KAPREKAR_TOP_RANGE_LIMIT = 9999;

    private static final int INTEGER_BASE = 10;

    private Task6() {
    }

    public static int countK(int n) {
        if (!(KAPREKAR_BOTTOM_RANGE_LIMIT < n
            && n < KAPREKAR_TOP_RANGE_LIMIT
            && !hasSameDigits(n))) {
            return -1;
        }

        int counter = 0;
        int result = n;
        while (result != KAPREKAR_CONST) {
            int min = Integer.parseInt(
                String.valueOf(result).chars()
                    .sorted()
                    .collect(
                        StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append
                    )
                    .toString()
            );
            int max = Integer.parseInt(
                String.valueOf(result).chars()
                    .sorted()
                    .collect(
                        StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append
                    )
                    .reverse()
                    .toString()
            );
            result = max - min;
            LOGGER.trace("{} - {} = {}", max, min, result);
            counter++;
        }
        return counter;
    }

    private static boolean hasSameDigits(int n) {
        int num = n;
        int digit = num % INTEGER_BASE;
        while (num > 0) {
            if (digit != (num % INTEGER_BASE)) {
                return false;
            }
            num /= INTEGER_BASE;
        }
        return true;
    }
}
