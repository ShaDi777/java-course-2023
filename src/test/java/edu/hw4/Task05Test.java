package edu.hw4;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task05Test {
    private final static Animal male = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal female = new Animal("Cat", Animal.Type.CAT, Animal.Sex.F, 1, 1, 1, true);

    private static Stream<Arguments> paramsAnimalsWithAnswer() {
        return Stream.of(
            Arguments.of(
                null,
                null
            ),

            Arguments.of(
                List.of(),
                null
            ),

            Arguments.of(
                List.of(male, female),
                null
            ),

            Arguments.of(
                List.of(male),
                Animal.Sex.M
            ),

            Arguments.of(
                List.of(female),
                Animal.Sex.F
            ),

            Arguments.of(
                List.of(female, male, female, male, female),
                Animal.Sex.F
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsAnimalsWithAnswer")
    void animalWithLongestNameTest(List<Animal> animals, Animal.Sex expectedResult) {
        var animal = StreamApiTasks.task5_WhichAnimalsMore(animals);

        assertThat(animal).isEqualTo(expectedResult);
    }
}
