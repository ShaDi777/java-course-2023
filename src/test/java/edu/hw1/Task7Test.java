package edu.hw1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    private static Stream<Arguments> paramsForLeftRotateWithAnswers() {
        return Stream.of(
            Arguments.of(16, 1, 1),
            Arguments.of(8, 1, 1),
            Arguments.of(17, 2, 6),
            Arguments.of(-1, 1, -1),
            Arguments.of(1, 1000, 1),
            Arguments.of(0, 10, 0),
            Arguments.of(2, 2, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("paramsForLeftRotateWithAnswers")
    void testLeftRotate(int num, int shift, int expectedResult) {
        // Arrange in parameters
        // Act
        int result = Task7.rotateLeft(num, shift);

        // Assert
        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> paramsForRightRotateWithAnswers() {
        return Stream.of(
            Arguments.of(8, 1, 4),
            Arguments.of(-1, 1, -1),
            Arguments.of(1, 1000, 1),
            Arguments.of(0, 10, 0),
            Arguments.of(2, 2, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("paramsForRightRotateWithAnswers")
    void testRightRotate(int num, int shift, int expectedResult) {
        // Arrange in parameters
        // Act
        int result = Task7.rotateRight(num, shift);

        // Assert
        assertThat(result).isEqualTo(expectedResult);
    }
}
