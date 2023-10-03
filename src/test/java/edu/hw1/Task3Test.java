package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    private static Stream<Arguments> paramsNotNestedArrays() {
        return Stream.of(
            Arguments.of(new long[] {}, new long[] {}),
            Arguments.of(new long[] {1}, new long[] {}),
            Arguments.of(new long[] {1}, new long[] {1}),
            Arguments.of(new long[] {9, 9, 8}, new long[] {8, 9}),
            Arguments.of(new long[] {1, 2, 3, 4}, new long[] {2, 3})
        );
    }
    @ParameterizedTest
    @MethodSource("paramsNotNestedArrays")
    void checkNotNestedArrays(long[] arr1, long[] arr2) {
        // Arrange in parameters
        // Act
        boolean result = Task3.isNestable(arr1, arr2);

        // Assert
        assertThat(result).isFalse();
    }


    private static Stream<Arguments> paramsNestedArrays() {
        return Stream.of(
            Arguments.of(new long[] {1, 2, 3, 4}, new long[] {0, 6}),
            Arguments.of(new long[] {3, 1}, new long[] {4, 0}),
            Arguments.of(new long[] {5}, new long[] {4, 6}),
            Arguments.of(new long[] {}, new long[] {1})
        );
    }
    @ParameterizedTest
    @MethodSource("paramsNestedArrays")
    void checkNestedArrays(long[] arr1, long[] arr2) {
        // Arrange in parameters
        // Act
        boolean result = Task3.isNestable(arr1, arr2);

        // Assert
        assertThat(result).isTrue();
    }
}
