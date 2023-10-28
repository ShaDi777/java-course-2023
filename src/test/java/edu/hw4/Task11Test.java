package edu.hw4;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task11Test {
    private final static Animal smallNotBites = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, false);
    private final static Animal bigNotBites = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 500, 1, false);
    private final static Animal smallBites = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal bigBites = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 500, 1, true);

    private static Stream<Arguments> paramsBigBitingAnimals() {
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
                List.of(smallNotBites),
                List.of()
            ),

            Arguments.of(
                List.of(smallBites),
                List.of()
            ),

            Arguments.of(
                List.of(bigNotBites),
                List.of()
            ),

            Arguments.of(
                List.of(bigBites, smallBites, smallNotBites, bigNotBites),
                List.of(bigBites)
            ),

            Arguments.of(
                List.of(bigBites, smallBites, smallNotBites, bigNotBites,
                        bigBites, smallBites, smallNotBites, bigNotBites),
                List.of(bigBites, bigBites)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsBigBitingAnimals")
    void listAnimalsBigAndBitesTest(List<Animal> animals, List<Animal> expectedResult) {
        var result = StreamApiTasks.task11_ListAnimalsBigAndBites(animals);

        assertThat(result).isEqualTo(expectedResult);
    }
}
