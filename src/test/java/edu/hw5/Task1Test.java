package edu.hw5;

import java.time.format.DateTimeParseException;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {

    private static Stream<Arguments> paramsTimeWithDuration() {
        return Stream.of(
            Arguments.of(
                "2022-03-12, 20:20 - 2022-03-12, 20:20",
                "PT0S"
            ),

            Arguments.of(
                "2022-03-12, 20:20 - 2022-03-12, 20:21",
                "PT1M"
            ),

            Arguments.of(
                "2022-03-12, 20:20 - 2022-03-12, 21:20",
                "PT1H"
            ),

            Arguments.of(
                "2022-03-12, 20:20 - 2022-03-12, 23:50",
                "PT3H30M"
            ),

            Arguments.of(
                "2022-03-12, 20:20 - 2022-03-13, 20:20",
                "PT24H"
            ),

            Arguments.of(
                "2022-03-12, 20:20 - 2022-03-14, 20:25",
                "PT48H5M"
            ),

            Arguments.of(
                "2022-03-12, 20:20 - 2022-04-12, 20:20",
                "PT744H" // 31 days * 24 hours = 744 hours
            ),

            // Negative duration
            Arguments.of(
                "2022-03-12, 20:20 - 2022-03-12, 19:20",
                "PT-1H"
            ),

            Arguments.of(
                "2022-03-12, 20:20 - 2022-03-12, 20:19",
                "PT-1M"
            ),

            Arguments.of(
                "2022-03-12, 20:20 - 2022-03-12, 19:19",
                "PT-1H-1M"
            ),

            Arguments.of(
                "2022-03-12, 20:20 - 2022-03-11, 20:20",
                "PT-24H"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsTimeWithDuration")
    void getDurationTest(String time, String expectedResult) {
        String result = Task1.getDuration(time);

        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> paramsInvalidFormat() {
        return Stream.of(
            Arguments.of((Object) null),

            Arguments.of(""),

            Arguments.of("2022-03-12, 20:20"),

            Arguments.of("2022-03-12, 20:20 // 2022-03-12, 20:21"),

            Arguments.of("2022-03-12 20:20 - 2022-03-12 21:20"),

            Arguments.of("20:20, 2022-03-12 - 23:50, 2022-03-12")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsInvalidFormat")
    void getDurationFromInvalidFormat(String invalidFormat) {
        assertThrows(IllegalArgumentException.class, () -> Task1.getDuration(invalidFormat));
    }

    private static Stream<Arguments> paramsInvalidDateTime() {
        return Stream.of(
            Arguments.of("2022-01-99, 20:20 - 2022-01-99, 21:20"),

            Arguments.of("2022-99-01, 20:20 - 2022-99-01, 21:20"),

            Arguments.of("2022-03-12, 99:20 - 2022-03-14, 00:25"),

            Arguments.of("2022-03-12, 21:20 - 2022-03-14, 20:61")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsInvalidDateTime")
    void getDurationFromInvalidDateTime(String invalidTime) {
        assertThrows(DateTimeParseException.class, () -> Task1.getDuration(invalidTime));
    }
}
