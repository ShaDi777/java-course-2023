package edu.hw5.DateParsers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Optional;

public class RelativeDateParser extends DateParser {
    @Override
    public Optional<LocalDate> tryParse(String date) {
        try {
            String[] dateArray = date.split(" ");
            int count = Integer.parseInt(dateArray[0]);
            TemporalUnit unit = switch (dateArray[1].toLowerCase()) {
                case "day", "days" -> ChronoUnit.DAYS;
                case "month", "months" -> ChronoUnit.MONTHS;
                case "year", "years" -> ChronoUnit.YEARS;
                case "week", "weeks" -> ChronoUnit.WEEKS;
                default -> throw new IllegalStateException();
            };

            String direction = dateArray[2];
            return switch (direction) {
                case "ago" -> Optional.of(LocalDate.now().minus(count, unit));
                case "after" -> Optional.of(LocalDate.now().plus(count, unit));
                default -> throw new IllegalStateException();
            };
        } catch (Exception e) {
            return parseNext(date);
        }
    }
}
