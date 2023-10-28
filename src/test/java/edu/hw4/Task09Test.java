package edu.hw4;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task09Test {
    private final static Animal spider = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal bird = new Animal("Birdie", Animal.Type.BIRD, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal fish = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 1, 1, 1, true);

    private static Stream<Arguments> paramsAnimalsWithPawsCount() {
        return Stream.of(
            Arguments.of(
                null,
                0
            ),

            Arguments.of(
                List.of(),
                0
            ),

            Arguments.of(
                List.of(dog),
                4
            ),

            Arguments.of(
                List.of(cat),
                4
            ),

            Arguments.of(
                List.of(fish),
                0
            ),

            Arguments.of(
                List.of(spider),
                8
            ),

            Arguments.of(
                List.of(bird),
                2
            ),

            Arguments.of(
                List.of(cat, dog, fish, spider, bird),
                18
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsAnimalsWithPawsCount")
    void totalPawsTest(List<Animal> animals, Integer expectedResult) {
        var totalPaws = StreamApiTasks.task9_TotalPaws(animals);

        assertThat(totalPaws).isEqualTo(expectedResult);
    }
}
