package edu.hw4;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task02Test {
    private final static Animal spider1 = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal bird5 = new Animal("Birdie", Animal.Type.BIRD, Animal.Sex.M, 1, 1, 5, true);
    private final static Animal cat10 = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 1, 10, true);
    private final static Animal cat15 = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 1, 15, true);
    private final static Animal dog15 = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 1, 1, 15, true);

    private static Stream<Arguments> paramsListOfAnimalsWithAnswer() {
        return Stream.of(
            Arguments.of(
                null, 10,
                List.of()
            ),

            Arguments.of(
                List.of(), 10,
                List.of()
            ),
            Arguments.of(
                List.of(spider1), 1,
                List.of(spider1)
            ),
            Arguments.of(
                List.of(spider1), 0,
                List.of()
            ),
            Arguments.of(
                List.of(dog15, bird5, cat10, cat15), 2,
                List.of(dog15, cat15)
            ),
            Arguments.of(
                List.of(dog15, bird5, cat10, cat15), 10000,
                List.of(dog15, cat15, cat10, bird5)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsListOfAnimalsWithAnswer")
    void sortDescByWeightTest(List<Animal> animals, int k, List<Animal> expectedResult) {
        List<Animal> sortedAnimals = StreamApiTasks.sortByWeightDescAndChooseFirstK(animals, k);

        assertThat(sortedAnimals).isEqualTo(expectedResult);
    }
}

