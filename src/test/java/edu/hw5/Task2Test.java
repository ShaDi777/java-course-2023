package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    public void testGetNextFriday13th() {
        LocalDate date = LocalDate.of(2023, 1, 1);
        LocalDate expectedDate = LocalDate.of(2023, 1, 13);

        LocalDate nextFriday13th = Task2.getNextFriday13th(date);

        assertThat(nextFriday13th).isEqualTo(expectedDate);
    }

    @ParameterizedTest
    @ValueSource(ints = {1925, 2023, 2024})
    public void testGetAllFriday13thInYear(int year) {
        List<LocalDate> fridayThe13ths = Task2.getAllFriday13thInYear(year);

        assertThat(fridayThe13ths.isEmpty()).isFalse();

        for (LocalDate date : fridayThe13ths) {
            assertThat(date.getDayOfWeek()).isEqualTo(DayOfWeek.FRIDAY);
            assertThat(date.getDayOfMonth()).isEqualTo(13);
            assertThat(date.getYear()).isEqualTo(year);
        }
    }

    @Test
    public void testGetAllFriday13thIn1925() {
        List<LocalDate> fridayThe13ths = Task2.getAllFriday13thInYear(1925);
        List<LocalDate> expectedResult = List.of(
            LocalDate.parse("1925-02-13"),
            LocalDate.parse("1925-03-13"),
            LocalDate.parse("1925-11-13")
        );

        assertThat(fridayThe13ths).isEqualTo(expectedResult);
    }
}
