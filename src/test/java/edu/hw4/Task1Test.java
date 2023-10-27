package edu.hw4;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    private final static Animal fish1 = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 1, 5, false);
    private final static Animal bird5 = new Animal("Birdie", Animal.Type.BIRD, Animal.Sex.M, 1, 5, 5, false);
    private final static Animal cat10 = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 10, 5, true);
    private final static Animal cat15 = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 15, 5, true);
    private final static Animal dog15 = new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 1, 15, 5, true);
    private final static Animal spider15 = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.F, 1, 15, 5, true);

    private static Stream<Arguments> paramsListOfAnimalsWithAnswer() {
        return Stream.of(
            Arguments.of(
                List.of(),
                List.of()
            ),
            Arguments.of(
                List.of(fish1),
                List.of(fish1)
            ),
            Arguments.of(
                List.of(spider15, bird5, cat10, fish1),
                List.of(fish1, bird5, cat10, spider15)
            ),
            Arguments.of(
                List.of(spider15, bird5, cat15, bird5, dog15, bird5),
                List.of(bird5, bird5, bird5, spider15, cat15, dog15)
            ),
            Arguments.of(
                List.of(spider15, cat15, dog15),
                List.of(spider15, cat15, dog15)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsListOfAnimalsWithAnswer")
    void sortAscByHeightTest(List<Animal> animals, List<Animal> expectedResult) {
        List<Animal> sortedAnimals = StreamApiTasks.task1_SortByHeightAsc(animals);

        assertThat(sortedAnimals).isEqualTo(expectedResult);
    }
}
