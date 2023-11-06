package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Task1 {
    private final static String DATETIME_PATTERN = "yyyy-MM-dd, HH:mm";
    private final static String SPLITTER = " - ";
    private final static String TIME_REGEX = "\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}";
    private final static String INPUT_TIME_REGEX = TIME_REGEX + SPLITTER + TIME_REGEX;

    private Task1() {
    }

    public static String getDuration(String time) {
        if (time == null || !time.matches(INPUT_TIME_REGEX)) {
            throw new IllegalArgumentException();
        }

        String[] timeSplit = time.split(SPLITTER);
        String from = timeSplit[0];
        String to = timeSplit[1];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
        LocalDateTime fromDateTime = LocalDateTime.parse(from, formatter);
        LocalDateTime toDateTime = LocalDateTime.parse(to, formatter);

        Duration duration = Duration.between(fromDateTime, toDateTime);
        return duration.toString();
    }
}
