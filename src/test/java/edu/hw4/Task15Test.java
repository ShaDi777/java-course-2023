package edu.hw4;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task15Test {
    private final static Animal animal1 = new Animal("", Animal.Type.DOG, Animal.Sex.M, 1, 1, 1, false);
    private final static Animal animal2 = new Animal("", Animal.Type.DOG, Animal.Sex.M, 2, 1, 2, false);
    private final static Animal animal3 = new Animal("", Animal.Type.DOG, Animal.Sex.M, 3, 1, 3, false);
    private final static Animal animal4 = new Animal("", Animal.Type.DOG, Animal.Sex.M, 4, 1, 4, true);
    private final static Animal animal5 = new Animal("", Animal.Type.DOG, Animal.Sex.M, 5, 1, 5, true);
    private final static Animal animal6 = new Animal("", Animal.Type.DOG, Animal.Sex.M, 6, 1, 6, true);

    private static Stream<Arguments> paramsTotalWeightOfAnimalsAgedFromKToL() {
        return Stream.of(
            Arguments.of(
                null,
                1, 1,
                0
            ),

            Arguments.of(
                null,
                1, 1,
                0
            ),

            Arguments.of(
                List.of(animal1, animal2),
                1, 1,
                1
            ),

            Arguments.of(
                List.of(animal1, animal2),
                1, 10,
                3
            ),

            Arguments.of(
                List.of(animal1, animal2, animal3, animal4, animal5, animal6),
                -10, 10,
                21
            ),

            Arguments.of(
                List.of(animal1, animal2, animal3, animal4, animal5, animal6),
                5, 6,
                11
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsTotalWeightOfAnimalsAgedFromKToL")
    void totalWeightOfAnimalsAgedFromKToLTest(List<Animal> animals, int k, int l, Integer expectedResult) {
        var result = StreamApiTasks.task15_TotalWeightOfAnimalsAgedFromKToL(animals, k, l);

        assertThat(result).isEqualTo(expectedResult);
    }
}
