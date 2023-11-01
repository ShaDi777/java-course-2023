package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public final class Task2 {
    private static final int FRIDAY_13TH_DAY = 13;

    private Task2() {
    }

    public static List<LocalDate> getAllFriday13thInYear(int year) {
        LocalDate currentStartDate = LocalDate.of(year, 1, 1);

        LocalDate nextFriday = getNextFriday13th(currentStartDate);
        List<LocalDate> fridayThe13ths = new ArrayList<>();
        while (nextFriday.getYear() == year) {
            fridayThe13ths.add(nextFriday);
            nextFriday = getNextFriday13th(nextFriday);
        }

        return fridayThe13ths;
    }

    public static LocalDate getNextFriday13th(LocalDate date) {
        LocalDate friday = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (friday.getDayOfMonth() != FRIDAY_13TH_DAY) {
            friday = friday.plusWeeks(1);
        }

        return friday;
    }
}
