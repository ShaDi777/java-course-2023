package edu.hw1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    private static Stream<Arguments> paramsNumsWithResults() {
        return Stream.of(
            Arguments.of(0, 1),
            Arguments.of(1, 1),
            Arguments.of(20, 2),
            Arguments.of(544, 3),
            Arguments.of(4666, 4),
            Arguments.of(-1, 1),
            Arguments.of(-10, 2),
            Arguments.of(-100, 3),
            Arguments.of(-1234, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("paramsNumsWithResults")
    void getTimeFromCorrectString(long num, int expectedResult) {
        // Arrange in parameters
        // Act
        int counter = Task2.countDigits(num);

        // Assert
        assertThat(counter).isEqualTo(expectedResult);
    }
}
