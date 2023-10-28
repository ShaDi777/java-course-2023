package edu.hw4;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task12Test {
    private final static Animal weightMoreThanHeight = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 500, false);
    private final static Animal heightMoreThanWeight = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 500, 1, false);
    private final static Animal equalWeightHeight = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, true);

    private static Stream<Arguments> paramsAnimalWeightMoreThanHeight() {
        return Stream.of(
            Arguments.of(
                null,
                0L
            ),

            Arguments.of(
                List.of(),
                0L
            ),

            Arguments.of(
                List.of(heightMoreThanWeight, equalWeightHeight),
                0L
            ),

            Arguments.of(
                List.of(weightMoreThanHeight),
                1L
            ),

            Arguments.of(
                List.of(weightMoreThanHeight, heightMoreThanWeight, equalWeightHeight, weightMoreThanHeight, weightMoreThanHeight),
                3L
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsAnimalWeightMoreThanHeight")
    void countAnimalWeightMoreThanHeightTest(List<Animal> animals, Long expectedResult) {
        var result = StreamApiTasks.task12_CountAnimalWeightMoreThanHeight(animals);

        assertThat(result).isEqualTo(expectedResult);
    }
}
