package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Objects;

public final class Task1 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task1() {
    }

    public static long minutesToSeconds(String time) {
        Objects.requireNonNull(time);
        LOGGER.trace("Decoding time in seconds from " + time);
        LOGGER.trace("Time matches regex? " + time.matches("\\d\\d+:\\d\\d"));

        if (!time.matches("\\d\\d+:\\d\\d")) return -1;

        int minutes = Integer.parseInt(time.split(":", 2)[0]);
        int seconds = Integer.parseInt(time.split(":", 2)[1]);
        LOGGER.trace("Get {} minutes and {} seconds ", minutes, seconds);

        if (seconds >= 60) return -1;

        return (minutes * 60L) + seconds;
    }
}
