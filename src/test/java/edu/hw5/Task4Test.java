package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    private static Stream<Arguments> paramsValidPasswords() {
        return Stream.of(
            Arguments.of("~"),
            Arguments.of("!word"),
            Arguments.of("word@"),
            Arguments.of("#$%"),
            Arguments.of("#$word%^"),
            Arguments.of("word~word"),
            Arguments.of("word!word"),
            Arguments.of("word@word"),
            Arguments.of("word#word"),
            Arguments.of("word$word"),
            Arguments.of("word%word"),
            Arguments.of("word^word"),
            Arguments.of("word&word"),
            Arguments.of("word*word"),
            Arguments.of("word|word")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsValidPasswords")
    void testValidPassword(String validPassword) {
        assertThat(Task4.isValidPassword(validPassword)).isTrue();
    }

    private static Stream<Arguments> paramsInvalidPasswords() {
        return Stream.of(
            Arguments.of((Object) null),
            Arguments.of(""),
            Arguments.of("abc"),
            Arguments.of("123"),
            Arguments.of("b/b"),
            Arguments.of("№5"),
            Arguments.of("...")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsInvalidPasswords")
    void testInvalidPassword(String invalidPassword) {
        assertThat(Task4.isValidPassword(invalidPassword)).isFalse();
    }
}
