package edu.hw5;

import edu.hw5.DateParsers.DateParser;
import edu.hw5.DateParsers.RelativeDateParser;
import edu.hw5.DateParsers.ShortDateParser;
import edu.hw5.DateParsers.StandartDateParser;
import edu.hw5.DateParsers.WordDateParser;
import java.time.LocalDate;
import java.util.Optional;

public final class Task3 {
    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String string) {
        DateParser head = DateParser.chain(
            new StandartDateParser(),
            new ShortDateParser(),
            new WordDateParser(),
            new RelativeDateParser()
        );

        return head.tryParse(string);
    }
}
