package edu.hw5.DateParsers;

import java.time.LocalDate;
import java.util.Optional;

public class ShortDateParser extends DateParser {
    @Override
    public Optional<LocalDate> tryParse(String date) {
        try {
            String[] dateArray = date.split("/");
            int day = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);
            int year = Integer.parseInt(dateArray[2]);

            return Optional.of(LocalDate.of(year, month, day));
        } catch (Exception e) {
            return parseNext(date);
        }
    }
}
