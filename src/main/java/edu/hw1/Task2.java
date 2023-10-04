package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task2 {
    private final static Logger LOGGER = LogManager.getLogger();

    private static final int INTEGER_BASE = 10;

    private Task2() {
    }

    public static int countDigits(long n) {
        long num = Math.abs(n);
        int counter = 0;
        do {
            num /= INTEGER_BASE;
            counter++;
        } while (num > 0);

        return counter;
    }
}
