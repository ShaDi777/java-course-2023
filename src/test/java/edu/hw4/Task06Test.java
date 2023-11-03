package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task06Test {
    private final static Animal spiderLow = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal spiderMedium = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 5, true);
    private final static Animal spiderHeavy = new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 10, true);

    private final static Animal birdLow = new Animal("Birdie", Animal.Type.BIRD, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal birdMedium = new Animal("Birdie", Animal.Type.BIRD, Animal.Sex.M, 1, 1, 5, true);
    private final static Animal birdHeavy  = new Animal("Birdie", Animal.Type.BIRD, Animal.Sex.M, 1, 1, 10, true);

    private final static Animal fishLow = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal fishMedium = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 1, 5, true);
    private final static Animal fishHeavy  = new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 1, 1, 10, true);

    private final static Animal catLow = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal catMedium = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 1, 5, true);
    private final static Animal catHeavy  = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 1, 10, true);

    private final static Animal dogLow = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal dogMedium = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 1, 1, 5, true);
    private final static Animal dogHeavy  = new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 1, 1, 10, true);

    private static Stream<Arguments> paramsMostHeavyInType() {
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
                List.of(catLow),
                Map.of(Animal.Type.CAT, catLow)
            ),

            Arguments.of(
                List.of(catLow, catHeavy, catMedium),
                Map.of(Animal.Type.CAT, catHeavy)
            ),

            Arguments.of(
                List.of(dogLow, dogMedium, dogHeavy,
                        fishLow, fishMedium, fishHeavy),
                Map.of(Animal.Type.DOG, dogHeavy,
                       Animal.Type.FISH, fishHeavy)
            ),

            Arguments.of(
                List.of(dogLow, dogMedium, dogHeavy,
                        fishLow, fishMedium, fishHeavy,
                        spiderLow, spiderMedium, spiderHeavy,
                        catLow, catMedium, catHeavy,
                        birdLow, birdMedium, birdHeavy),
                Map.of(Animal.Type.DOG, dogHeavy,
                       Animal.Type.FISH, fishHeavy,
                       Animal.Type.SPIDER, spiderHeavy,
                       Animal.Type.CAT, catHeavy,
                       Animal.Type.BIRD, birdHeavy)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsMostHeavyInType")
    void mostHeavyAnimalsTest(List<Animal> animals, Map<Animal.Type, Animal> expectedResult) {
        var animal = StreamApiTasks.typeMaxWeightAnimalMap(animals);

        assertThat(animal).isEqualTo(expectedResult);
    }
}
