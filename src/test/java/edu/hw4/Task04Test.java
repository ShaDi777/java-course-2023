package edu.hw4;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task04Test {
    private final static Animal name1 = new Animal("S", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal name3 = new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 1, 1, true);

    private final static Animal name4 = new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal name6 = new Animal("Fishie", Animal.Type.FISH, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal name15 = new Animal("Abcdefghijklmno", Animal.Type.DOG, Animal.Sex.M, 1, 1, 1, true);

    private static Stream<Arguments> paramsAnimalsWithLongestNames() {
        return Stream.of(
            Arguments.of(
                List.of(name1),
                name1
            ),

            Arguments.of(
                List.of(name3, name1, name4),
                name4
            ),

            Arguments.of(
                List.of(name15, name1, name6),
                name15
            ),

            Arguments.of(
                List.of(name4, name3, name15, name1, name6),
                name15
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsAnimalsWithLongestNames")
    void animalWithLongestNameTest(List<Animal> animals, Animal expectedResult) {
        var animal = StreamApiTasks.findAnimalWithLongestName(animals);

        assertThat(animal).isEqualTo(expectedResult);
    }
}
