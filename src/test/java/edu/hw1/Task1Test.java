package edu.hw1;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class Task1Test {
    @Test
    void getTimeFromNull() {
        // Arrange
        String time = null;

        // Act
        Throwable thrown = catchThrowable(() -> Task1.minutesToSeconds(time));

        // Assert
        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(
        strings = {
            "NotTimeString",
            "Просто текст",
            "100",
            "!@#$%^&",
            ":",
            "0:0",
            "12:",
            ":12",
            "1:10",
            "10:0",
            "10:60",
            "11:99"
        }
    )
    void getTimeFromIncorrectString(String time) {
        // Arrange in parameters
        // Act
        long timeInSeconds = Task1.minutesToSeconds(time);

        // Assert
        assertThat(timeInSeconds).isEqualTo(-1);
    }

    private static Stream<Arguments> paramsCorrectStringWithResults() {
        return Stream.of(
            Arguments.of("00:00", 0),
            Arguments.of("00:59", 59),
            Arguments.of("01:00", 60),
            Arguments.of("13:56", 836),
            Arguments.of("999:59", 59999)
        );
    }

    @ParameterizedTest
    @MethodSource("paramsCorrectStringWithResults")
    void getTimeFromCorrectString(String time, long expectedResult) {
        // Arrange in parameters
        // Act
        long timeInSeconds = Task1.minutesToSeconds(time);

        // Assert
        assertThat(timeInSeconds).isEqualTo(expectedResult);
    }
}
