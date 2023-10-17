package edu.hw3;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {

    private static Stream<Arguments> paramsStringsAndFrequency() {
        return Stream.of(
            Arguments.of(
                List.of("a", "bb", "a", "bb"),
                Map.of("a", 2, "bb", 2)
            ),
            Arguments.of(
                List.of("this", "and", "that", "and"),
                Map.of("that", 1, "and", 2, "this", 1)
            ),
            Arguments.of(
                List.of("код", "код", "код", "bug"),
                Map.of("код", 3, "bug", 1)
            ),
            Arguments.of(
                List.of(),
                Map.of()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsStringsAndFrequency")
    void freqDictStringTest(List<String> words, Map<String, Integer> expectedResult) {
        Map<String, Integer> actualResult = Task3.freqDict(words);
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void freqDictIntegerTest() {
        List<Integer> objects = List.of(1, 1, 2, 2);
        Map<Integer, Integer> expectedResult = Map.of(1, 2, 2, 2);

        Map<Integer, Integer> actualResult = Task3.freqDict(objects);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void freqDictBooleanTest() {
        List<Boolean> objects = List.of(true, false, true, false, true);
        Map<Boolean, Integer> expectedResult = Map.of(true, 3, false, 2);

        Map<Boolean, Integer> actualResult = Task3.freqDict(objects);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void freqDictListTest() {
        List<List<String>> objects = List.of(
            List.of("a", "b"),
            List.of("a", "b"),
            List.of("a", "b"),
            List.of("a"),
            List.of("b"),
            List.of("b")
        );
        Map<List<String>, Integer> expectedResult = Map.of(
            List.of("a", "b"), 3,
            List.of("a"), 1,
            List.of("b"), 2
        );

        Map<List<String>, Integer> actualResult = Task3.freqDict(objects);

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
