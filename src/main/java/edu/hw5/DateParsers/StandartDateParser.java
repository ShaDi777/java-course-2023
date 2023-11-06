package edu.hw5.DateParsers;

import java.time.LocalDate;
import java.util.Optional;

public class StandartDateParser extends DateParser {
    private final static String PARSING_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    @Override
    public Optional<LocalDate> tryParse(String date) {
        if (date == null || !date.matches(PARSING_REGEX)) {
            return parseNext(date);
        }

        return Optional.of(LocalDate.parse(date));
    }
}
