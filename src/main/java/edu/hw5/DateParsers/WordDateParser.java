package edu.hw5.DateParsers;

import java.time.LocalDate;
import java.util.Optional;

public class WordDateParser extends DateParser {

    @Override
    public Optional<LocalDate> tryParse(String date) {
        return switch (date.toLowerCase()) {
            case "today" -> Optional.of(LocalDate.now());
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            case "the day after tomorrow" -> Optional.of(LocalDate.now().plusDays(2));
            case "yesterday" -> Optional.of(LocalDate.now().minusDays(1));
            case "the day before yesterday" -> Optional.of(LocalDate.now().minusDays(2));
            default -> parseNext(date);
        };
    }
}
