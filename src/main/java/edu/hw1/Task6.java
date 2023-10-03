package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public final class Task6 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task6() {
    }

    public static int countK(int n) {
        if (n <= 1000 || n > 9999 || (n / 1111) * 1111 == n) {
            return -1;
        }

        int counter = 0;
        int result = n;
        while (result != 6174) {
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
}
