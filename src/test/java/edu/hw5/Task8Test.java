package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {
    // Test 1 subtask
    private static Stream<Arguments> paramsValidStrings1() {
        return Stream.of(
            Arguments.of("1"),
            Arguments.of("101"),
            Arguments.of("01111")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsValidStrings1")
    void testSubtask1True(String string) {
        assertThat(Task8.lengthOdd(string)).isTrue();
    }

    private static Stream<Arguments> paramsInvalidStrings1() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("10"),
            Arguments.of("1111"),
            Arguments.of("110011")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsInvalidStrings1")
    void testSubtask1False(String string) {
        assertThat(Task8.lengthOdd(string)).isFalse();
    }

    // Test 2 subtask
    private static Stream<Arguments> paramsValidStrings2() {
        return Stream.of(
            Arguments.of("0"),
            Arguments.of("10"),
            Arguments.of("011"),
            Arguments.of("1001")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsValidStrings2")
    void testSubtask2True(String string) {
        assertThat(Task8.zeroWithOddLengthOrOneWithEvenLength(string)).isTrue();
    }

    private static Stream<Arguments> paramsInvalidStrings2() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("1"),
            Arguments.of("00"),
            Arguments.of("0000"),
            Arguments.of("111")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsInvalidStrings2")
    void testSubtask2False(String string) {
        assertThat(Task8.zeroWithOddLengthOrOneWithEvenLength(string)).isFalse();
    }

    // Test 3 subtask
    private static Stream<Arguments> paramsValidStrings3() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("11"),
            Arguments.of("10001"),
            Arguments.of("000"),
            Arguments.of("1010101")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsValidStrings3")
    void testSubtask3True(String string) {
        assertThat(Task8.zeroCountDivisibleBy3(string)).isTrue();
    }

    private static Stream<Arguments> paramsInvalidStrings3() {
        return Stream.of(
            Arguments.of("0"),
            Arguments.of("101"),
            Arguments.of("010"),
            Arguments.of("000100")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsInvalidStrings3")
    void testSubtask3False(String string) {
        assertThat(Task8.zeroCountDivisibleBy3(string)).isFalse();
    }

    // Test 4 subtask
    private static Stream<Arguments> paramsValidStrings4() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("110"),
            Arguments.of("1110"),
            Arguments.of("011"),
            Arguments.of("01111")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsValidStrings4")
    void testSubtask4True(String string) {
        assertThat(Task8.not11or111(string)).isTrue();
    }

    private static Stream<Arguments> paramsInvalidStrings4() {
        return Stream.of(
            Arguments.of("11"),
            Arguments.of("111")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsInvalidStrings4")
    void testSubtask4False(String string) {
        assertThat(Task8.not11or111(string)).isFalse();
    }

    // Test 5 subtask
    private static Stream<Arguments> paramsValidStrings5() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("1"),
            Arguments.of("101"),
            Arguments.of("111"),
            Arguments.of("101010")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsValidStrings5")
    void testSubtask5True(String string) {
        assertThat(Task8.everyOddPositionIs1(string)).isTrue();
    }

    private static Stream<Arguments> paramsInvalidStrings5() {
        return Stream.of(
            Arguments.of("0"),
            Arguments.of("100"),
            Arguments.of("001"),
            Arguments.of("10100")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsInvalidStrings5")
    void testSubtask5False(String string) {
        assertThat(Task8.everyOddPositionIs1(string)).isFalse();
    }

    // Test 6 subtask
    private static Stream<Arguments> paramsValidStrings6() {
        return Stream.of(
            Arguments.of("100"),
            Arguments.of("000"),
            Arguments.of("00"),
            Arguments.of("0001")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsValidStrings6")
    void testSubtask6True(String string) {
        assertThat(Task8.twoAndMoreZerosWithOneAndLessOnes(string)).isTrue();
    }

    private static Stream<Arguments> paramsInvalidStrings6() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("110"),
            Arguments.of("0"),
            Arguments.of("1"),
            Arguments.of("10"),
            Arguments.of("1001")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsInvalidStrings6")
    void testSubtask6False(String string) {
        assertThat(Task8.twoAndMoreZerosWithOneAndLessOnes(string)).isFalse();
    }

    // Test 6 subtask
    private static Stream<Arguments> paramsValidStrings7() {
        return Stream.of(
            Arguments.of(""),
            Arguments.of("1"),
            Arguments.of("000"),
            Arguments.of("010")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsValidStrings7")
    void testSubtask7True(String string) {
        assertThat(Task8.noConsecutiveOnes(string)).isTrue();
    }

    private static Stream<Arguments> paramsInvalidStrings7() {
        return Stream.of(
            Arguments.of("11"),
            Arguments.of("001011"),
            Arguments.of("0110"),
            Arguments.of("11000"),
            Arguments.of("11111"),
            Arguments.of("01010001100")
        );
    }

    @ParameterizedTest
    @MethodSource("paramsInvalidStrings7")
    void testSubtask7False(String string) {
        assertThat(Task8.noConsecutiveOnes(string)).isFalse();
    }
}
