package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    private static Stream<Arguments> paramsStringAndClusters() {
        return Stream.of(
            Arguments.of(null, new ArrayList<>()),
            Arguments.of("", new ArrayList<>()),
            Arguments.of("()", Arrays.stream((new String[] { "()" })).toList()),
            Arguments.of("((()))", Arrays.stream((new String[] { "((()))" })).toList()),
            Arguments.of("()()()", Arrays.stream((new String[] { "()", "()", "()" })).toList()),
            Arguments.of("((()))(())()()(()())", Arrays.stream((new String[] { "((()))", "(())", "()", "()", "(()())" })).toList()),
            Arguments.of("((())())(()(()()))", Arrays.stream((new String[] { "((())())", "(()(()()))" })).toList()),
            Arguments.of("(a)(b)(c)(((", Arrays.stream((new String[] { "(a)", "(b)", "(c)" })).toList())
        );
    }

    @ParameterizedTest
    @MethodSource("paramsStringAndClusters")
    void clusterizeTest(String text, List<String> expectedResult) {
        List<String> actualResult = Task2.clusterize(text);

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
