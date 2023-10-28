package edu.hw4;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task13Test {
    private final static Animal nameEmpty = new Animal("", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, false);
    private final static Animal nameOneWord = new Animal("One", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, false);
    private final static Animal nameTwoWords = new Animal("One Two", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, false);
    private final static Animal nameThreeWords =
        new Animal("One Two Three", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, true);

    private static Stream<Arguments> paramsAnimalsNameHasMoreThanTwoWords() {
        return Stream.of(
            Arguments.of(
                null,
                List.of()
            ),

            Arguments.of(
                List.of(),
                List.of()
            ),

            Arguments.of(
                List.of(nameEmpty, nameOneWord, nameTwoWords),
                List.of()
            ),

            Arguments.of(
                List.of(nameThreeWords),
                List.of(nameThreeWords)
            ),

            Arguments.of(
                List.of(
                    nameThreeWords,
                    nameEmpty,
                    nameOneWord,
                    nameTwoWords,
                    nameThreeWords
                ),
                List.of(nameThreeWords, nameThreeWords)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsAnimalsNameHasMoreThanTwoWords")
    void listAnimalsNameHasMoreThanTwoWordsTest(List<Animal> animals, List<Animal> expectedResult) {
        var result = StreamApiTasks.task13_ListAnimalsNameHasMoreThanTwoWords(animals);

        assertThat(result).isEqualTo(expectedResult);
    }
}
