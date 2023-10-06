package edu.hw1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    private static Stream<Arguments> paramsKaprekarNumbers() {
        return Stream.of(
            Arguments.of(3524, 3),
            Arguments.of(6621, 5),
            Arguments.of(6554, 4),
            Arguments.of(1234, 3),
            Arguments.of(1001, 4),
            Arguments.of(1337, 6),
            Arguments.of(6174, 0),
            Arguments.of(0, -1),
            Arguments.of(8888, -1),
            Arguments.of(99999, -1)
        );
    }

    @ParameterizedTest
    @MethodSource("paramsKaprekarNumbers")
    void testKaprekarNumber(int num, int expectedResult) {
        // Arrange in parameters
        // Act
        int result = Task6.countK(num);

        // Assert
        assertThat(result).isEqualTo(expectedResult);
    }
}
