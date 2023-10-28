package edu.hw4;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task07Test {
    private final static Animal age1 = new Animal("A", Animal.Type.CAT, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal age2 = new Animal("A", Animal.Type.CAT, Animal.Sex.M, 2, 1, 1, true);
    private final static Animal age3 = new Animal("A", Animal.Type.CAT, Animal.Sex.M, 3, 1, 1, true);
    private final static Animal age4 = new Animal("A", Animal.Type.CAT, Animal.Sex.M, 4, 1, 1, true);
    private final static Animal age5 = new Animal("A", Animal.Type.CAT, Animal.Sex.M, 5, 1, 1, true);
    private final static Animal age6 = new Animal("A", Animal.Type.CAT, Animal.Sex.M, 6, 1, 1, true);
    private final static Animal age7 = new Animal("A", Animal.Type.CAT, Animal.Sex.M, 7, 1, 1, true);

    private static Stream<Arguments> paramsAnimalKthAged() {
        return Stream.of(
            Arguments.of(
                null, 1,
                null
            ),

            Arguments.of(
                List.of(), 5,
                null
            ),

            Arguments.of(
                List.of(age1), 1,
                age1
            ),

            Arguments.of(
                List.of(age1), 2,
                null
            ),

            Arguments.of(
                List.of(age7, age1, age2, age6), 2,
                age6
            ),

            Arguments.of(
                List.of(age1, age2, age3, age4, age5, age6, age7), 1,
                age7
            ),

            Arguments.of(
                List.of(age7, age6, age5, age4, age3, age2, age1), 5,
                age3
            ),

            Arguments.of(
                List.of(age1, age1, age1, age1, age1, age1, age1), 4,
                age1
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsAnimalKthAged")
    void KthAnimalAgeTest(List<Animal> animals, int k, Animal expectedResult) {
        var animal = StreamApiTasks.task7_KthEldestAnimal(animals, k);

        assertThat(animal).isEqualTo(expectedResult);
    }
}
