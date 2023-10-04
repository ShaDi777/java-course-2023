package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @ParameterizedTest
    @ValueSource(longs = {11, 123, 3140, 11211230, 13001120, 23336014})
    void testPalindromes(long num) {
        // Arrange in parameters
        // Act
        boolean result = Task5.isPalindromeDescendant(num);

        // Assert
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @ValueSource(longs = {0, 1, 12, 124, 1211})
    void testNotPalindromes(long num) {
        // Arrange in parameters
        // Act
        boolean result = Task5.isPalindromeDescendant(num);

        // Assert
        assertThat(result).isFalse();
    }
}
