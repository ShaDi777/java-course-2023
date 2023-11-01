package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task6Test {
    private static Stream<Arguments> paramsWithSubstring() {
        return Stream.of(
            Arguments.of("", ""),
            Arguments.of("", "abc"),
            Arguments.of("a", "a"),
            Arguments.of("abc", "qwertyabczxcvbnm"),
            Arguments.of("abc", "abcdefgh"),
            Arguments.of("abc", "zxcabc")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsWithSubstring")
    void testHasSubstring(String pattern, String string) {
        assertThat(Task6.hasSubstring(pattern, string)).isTrue();
    }

    private static Stream<Arguments> paramsNoSubstring() {
        return Stream.of(
            Arguments.of("a", ""),
            Arguments.of("abc", "ab"),
            Arguments.of("abba", "abbba"),
            Arguments.of("abc", "cba"),
            Arguments.of("a", "bcdefg")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsNoSubstring")
    void testNoSubstring(String pattern, String string) {
        assertThat(Task6.hasSubstring(pattern, string)).isFalse();
    }
}
