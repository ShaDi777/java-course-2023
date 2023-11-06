package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    private static Stream<Arguments> paramsDateWithOptionalResult() {
        return Stream.of(
            Arguments.of(
                "Today",
                Optional.of(LocalDate.now())
            ),

            Arguments.of(
                "YesterdaY",
                Optional.of(LocalDate.now().minusDays(1))
            ),

            Arguments.of(
                "ToMoRrOw",
                Optional.of(LocalDate.now().plusDays(1))
            ),

            Arguments.of(
                "2020-10-10",
                Optional.of(LocalDate.of(2020, 10, 10))
            ),

            Arguments.of(
                "2020-12-02",
                Optional.of(LocalDate.of(2020, 12, 2))
            ),

            Arguments.of(
                "1/3/1976",
                Optional.of(LocalDate.of(1976, 3, 1))
            ),

            Arguments.of(
                "1 day ago",
                Optional.of(LocalDate.now().minusDays(1))
            ),

            Arguments.of(
                "1 week ago",
                Optional.of(LocalDate.now().minusWeeks(1))
            ),

            Arguments.of(
                "1 month ago",
                Optional.of(LocalDate.now().minusMonths(1))
            ),

            Arguments.of(
                "1 year ago",
                Optional.of(LocalDate.now().minusYears(1))
            ),

            Arguments.of(
                "2234 day ago",
                Optional.of(LocalDate.now().minusDays(2234))
            ),

            Arguments.of(
                null,
                Optional.empty()
            ),

            Arguments.of(
                "",
                Optional.empty()
            ),

            Arguments.of(
                "2022",
                Optional.empty()
            ),

            Arguments.of(
                "19 03 1903",
                Optional.empty()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsDateWithOptionalResult")
    void testDateParser(String date, Optional<LocalDate> expectedResult) {
        assertThat(Task3.parseDate(date)).isEqualTo(expectedResult);
    }
}
