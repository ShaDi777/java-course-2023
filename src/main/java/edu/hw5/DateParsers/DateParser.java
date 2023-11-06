package edu.hw5.DateParsers;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {
    private DateParser next;

    public static DateParser chain(DateParser first, DateParser... chain) {
        DateParser head = first;
        for (DateParser nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract Optional<LocalDate> tryParse(String date);

    protected Optional<LocalDate> parseNext(String date) {
        if (next == null) {
            return Optional.empty();
        }
        return next.tryParse(date);
    }
}
