package edu.hw5.DateParsers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class StandartDateParser extends DateParser {
    @Override
    public Optional<LocalDate> tryParse(String date) {
        try {
            return Optional.of(LocalDate.parse(date));
        } catch (DateTimeParseException e) {
            return parseNext(date);
        }
    }
}
