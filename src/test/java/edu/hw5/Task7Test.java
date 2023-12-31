package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    // Test 1 subtask
    private static Stream<Arguments> paramsValidStrings1() {
        return Stream.of(
            Arguments.of("000"),
            Arguments.of("01010"),
            Arguments.of("110111111")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsValidStrings1")
    void testSubtask1True(String string) {
        assertThat(Task7.moreThanThreeSymbolsThirdIsZero(string)).isTrue();
    }

    private static Stream<Arguments> paramsInvalidStrings1() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("1"),
            Arguments.of("11"),
            Arguments.of("1110"),
            Arguments.of("001")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsInvalidStrings1")
    void testSubtask1False(String string) {
        assertThat(Task7.moreThanThreeSymbolsThirdIsZero(string)).isFalse();
    }

    // Test 2 subtask
    private static Stream<Arguments> paramsValidStrings2() {
        return Stream.of(
            Arguments.of("1"),
            Arguments.of("0"),
            Arguments.of("000"),
            Arguments.of("01010"),
            Arguments.of("110111111")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsValidStrings2")
    void testSubtask2True(String string) {
        assertThat(Task7.startsAndEndsWithSameSymbol(string)).isTrue();
    }

    private static Stream<Arguments> paramsInvalidStrings2() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("01"),
            Arguments.of("110"),
            Arguments.of("1110"),
            Arguments.of("001")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsInvalidStrings2")
    void testSubtask2False(String string) {
        assertThat(Task7.startsAndEndsWithSameSymbol(string)).isFalse();
    }

    // Test 3 subtask
    private static Stream<Arguments> paramsValidStrings3() {
        return Stream.of(
            Arguments.of("1"),
            Arguments.of("0"),
            Arguments.of("11"),
            Arguments.of("00"),
            Arguments.of("111"),
            Arguments.of("000")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsValidStrings3")
    void testSubtask3True(String string) {
        assertThat(Task7.lengthBetweenOneAndThree(string)).isTrue();
    }

    private static Stream<Arguments> paramsInvalidStrings3() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("1111"),
            Arguments.of("010101"),
            Arguments.of("111111")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsInvalidStrings3")
    void testSubtask3False(String string) {
        assertThat(Task7.lengthBetweenOneAndThree(string)).isFalse();
    }
}
