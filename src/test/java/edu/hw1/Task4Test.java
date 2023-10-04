package edu.hw1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    private static Stream<Arguments> paramsBrokenAndFixedStrings() {
        return Stream.of(
            Arguments.of("abc", "bac"),
            Arguments.of("123456", "214365"),
            Arguments.of("badce", "abcde"),
            Arguments.of("hTsii  s aimex dpus rtni.g", "This is a mixed up string."),
            Arguments.of("", ""),
            Arguments.of("Русские буквы.", "уРссик еубвк.ы")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsBrokenAndFixedStrings")
    void checkNotNestedArrays(String broken, String expectedFix) {
        // Arrange in parameters
        // Act
        String result = Task4.fixString(broken);

        // Assert
        assertThat(result).isEqualTo(expectedFix);
    }
}
