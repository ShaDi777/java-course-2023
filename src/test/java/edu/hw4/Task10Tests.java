package edu.hw4;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task10Tests {
    private final static Animal spiderUnusable = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 8, 1, 1, true);
    private final static Animal birdUnusable = new Animal("Birdie", Animal.Type.BIRD, Animal.Sex.M, 2, 1, 1, true);
    private final static Animal fishUnusable = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 0, 1, 1, true);
    private final static Animal catUnusable = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 4, 1, 1, true);
    private final static Animal dogUnusable = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 4, 1, 1, true);

    private final static Animal spider = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 10, 1, 1, true);
    private final static Animal bird = new Animal("Birdie", Animal.Type.BIRD, Animal.Sex.M, 10, 1, 1, true);
    private final static Animal fish = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 10, 1, 1, true);
    private final static Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 10, 1, 1, true);
    private final static Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 10, 1, 1, true);

    private static Stream<Arguments> paramsAnimalsWithPawsCount() {
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
                List.of(catUnusable),
                List.of()
            ),

            Arguments.of(
                List.of(catUnusable, dogUnusable, fishUnusable, spiderUnusable, birdUnusable),
                List.of()
            ),

            Arguments.of(
                List.of(fish),
                List.of(fish)
            ),

            Arguments.of(
                List.of(spider),
                List.of(spider)
            ),

            Arguments.of(
                List.of(bird),
                List.of(bird)
            ),

            Arguments.of(
                List.of(
                    cat,
                    dog,
                    fish,
                    spider,
                    bird,
                    catUnusable,
                    dogUnusable,
                    fishUnusable,
                    spiderUnusable,
                    birdUnusable
                ),
                List.of(cat, dog, fish, spider, bird)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsAnimalsWithPawsCount")
    void listAnimalsAgeNotEqualsPawsTest(List<Animal> animals, List<Animal> expectedResult) {
        var result = StreamApiTasks.task10_ListAnimalsAgeNotEqualsPaws(animals);

        assertThat(result).isEqualTo(expectedResult);
    }
}
