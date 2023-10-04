package edu.hw1;

import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task1 {
    private final static Logger LOGGER = LogManager.getLogger();

    private static final int SECONDS_IN_MINUTE = 60;

    private Task1() {
    }

    public static long minutesToSeconds(String time) {
        Objects.requireNonNull(time);
        LOGGER.trace("Decoding time in seconds from " + time);
        LOGGER.trace("Time matches regex? " + matchesFormat(time));

        if (!matchesFormat(time)) {
            return -1;
        }

        int minutes = Integer.parseInt(time.split(":", 2)[0]);
        int seconds = Integer.parseInt(time.split(":", 2)[1]);
        LOGGER.trace("Get {} minutes and {} seconds ", minutes, seconds);

        if (seconds >= SECONDS_IN_MINUTE) {
            return -1;
        }

        return ((long) minutes * SECONDS_IN_MINUTE) + seconds;
    }

    private static boolean matchesFormat(String time) {
        return time.matches("\\d\\d+:\\d\\d");
    }
}
