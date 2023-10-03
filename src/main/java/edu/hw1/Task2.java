package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task2 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task2() {
    }

    public static int countDigits(long num) {
        num = Math.abs(num);
        int counter = 0;
        do {
            num /= 10;
            counter++;
        } while (num > 0);

        return counter;
    }
}
