package edu.hw4;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;


public class Task14Test {
    private final static Animal cat = new Animal("", Animal.Type.CAT, Animal.Sex.M, 1, 100, 1, false);
    private final static Animal fish = new Animal("", Animal.Type.FISH, Animal.Sex.M, 1, 100, 1, false);
    private final static Animal spider = new Animal("", Animal.Type.SPIDER, Animal.Sex.M, 1, 100, 1, false);
    private final static Animal dog5cm = new Animal("", Animal.Type.DOG, Animal.Sex.M, 1, 5, 1, true);
    private final static Animal dog50cm = new Animal("", Animal.Type.DOG, Animal.Sex.M, 1, 50, 1, true);
    private final static Animal dog100cm = new Animal("", Animal.Type.DOG, Animal.Sex.M, 1, 100, 1, true);

    private static Stream<Arguments> paramsContainsDogWithHigherThanKcm() {
        return Stream.of(
            Arguments.of(
                null, 1,
                false
            ),

            Arguments.of(
                List.of(), 1,
                false
            ),

            Arguments.of(
                List.of(cat, fish, spider, dog5cm), 10,
                false
            ),

            Arguments.of(
                List.of(cat, fish, spider, dog50cm), 10,
                true
            ),

            Arguments.of(
                List.of(dog50cm, cat, fish, spider, dog100cm), 70,
                true
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsContainsDogWithHigherThanKcm")
    void containsDogWithHigherThanKcmTest(List<Animal> animals, int k, Boolean expectedResult) {
        var result = StreamApiTasks.containsDogWithHigherThanKcm(animals, k);

        assertThat(result).isEqualTo(expectedResult);
    }
}
