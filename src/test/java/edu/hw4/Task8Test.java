package edu.hw4;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {
    private final static Animal light3cm = new Animal("A", Animal.Type.FISH, Animal.Sex.M, 1, 3, 5, true);
    private final static Animal medium3cm = new Animal("A", Animal.Type.FISH, Animal.Sex.M, 1, 3, 10, true);
    private final static Animal heavy3cm = new Animal("A", Animal.Type.FISH, Animal.Sex.M, 1, 3, 15, true);

    private final static Animal light10cm = new Animal("A", Animal.Type.FISH, Animal.Sex.M, 1, 10, 5, true);
    private final static Animal medium10cm = new Animal("A", Animal.Type.FISH, Animal.Sex.M, 1, 10, 10, true);
    private final static Animal heavy10cm = new Animal("A", Animal.Type.FISH, Animal.Sex.M, 1, 10, 15, true);

    private static Stream<Arguments> paramsHeavyAnimalsBelowKcm() {
        return Stream.of(
            Arguments.of(
                null, 1,
                Optional.empty()
            ),

            Arguments.of(
                List.of(), 5,
                Optional.empty()
            ),

            Arguments.of(
                List.of(light3cm), 5,
                Optional.of(light3cm)
            ),

            Arguments.of(
                List.of(light3cm, heavy10cm), 5,
                Optional.of(light3cm)
            ),

            Arguments.of(
                List.of(light3cm, heavy10cm), 1000,
                Optional.of(heavy10cm)
            ),

            Arguments.of(
                List.of(light3cm, medium3cm, light10cm, heavy10cm), 5,
                Optional.of(medium3cm)
            ),

            Arguments.of(
                List.of(light3cm, medium3cm, light10cm, heavy10cm), 1000,
                Optional.of(heavy10cm)
            ),

            Arguments.of(
                List.of(light3cm, medium3cm, heavy3cm, light10cm, medium10cm, heavy10cm), 1000,
                Optional.of(heavy3cm)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsHeavyAnimalsBelowKcm")
    void maxWeightedAnimalAmongLowerThanKcmTest(List<Animal> animals, int k, Optional<Animal> expectedResult) {
        var animal = StreamApiTasks.task8_MaxWeightedAnimalAmongLowerThanKcm(animals, k);

        assertThat(animal).isEqualTo(expectedResult);
    }
}
