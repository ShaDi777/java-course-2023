package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task03Test {
    private final static Animal spider = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal bird = new Animal("Birdie", Animal.Type.BIRD, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal fish = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal cat = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal dog = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 1, 1, 1, true);

    private static Stream<Arguments> paramsListOfAnimalsWithFrequencyMap() {
        return Stream.of(
            Arguments.of(
                null,
                Map.of()
            ),

            Arguments.of(
                List.of(),
                Map.of()
            ),

            Arguments.of(
                List.of(dog),
                Map.of(
                    Animal.Type.DOG, 1
                )
            ),

            Arguments.of(
                List.of(dog, dog, cat, dog),
                Map.of(
                    Animal.Type.DOG, 3,
                    Animal.Type.CAT, 1
                )
            ),

            Arguments.of(
                List.of(dog, fish, cat, bird, spider),
                Map.of(
                    Animal.Type.DOG, 1,
                    Animal.Type.FISH, 1,
                    Animal.Type.CAT, 1,
                    Animal.Type.BIRD, 1,
                    Animal.Type.SPIDER, 1
                )
            ),

            Arguments.of(
                List.of(dog, fish, cat, bird, spider,
                        dog, fish, cat, bird, spider,
                        dog, fish, cat, bird, spider,
                        dog, fish, cat, bird, spider),
                Map.of(
                    Animal.Type.DOG, 4,
                    Animal.Type.FISH, 4,
                    Animal.Type.CAT, 4,
                    Animal.Type.BIRD, 4,
                    Animal.Type.SPIDER, 4
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsListOfAnimalsWithFrequencyMap")
    void frequencyMapFromListTest(List<Animal> animals, Map<Animal.Type, Integer> expectedResult) {
        var frequencyMap = StreamApiTasks.task3_TypeCountMap(animals);

        assertThat(frequencyMap).isEqualTo(expectedResult);
    }
}
