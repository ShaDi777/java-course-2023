package edu.hw5.DateParsers;

import java.time.LocalDate;
import java.util.Optional;

public class ShortDateParser extends DateParser {
    private final static String PARSING_REGEX = "\\d{1,2}/\\d{1,2}/\\d{4}";

    @Override
    public Optional<LocalDate> tryParse(String date) {
        if (date == null || !date.matches(PARSING_REGEX)) {
            return parseNext(date);
        }

        String[] dateArray = date.split("/");
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);

        return Optional.of(LocalDate.of(year, month, day));
    }
}
