package edu.hw4;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task17Test {
    private final static Animal spiderBites = new Animal("", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal spiderNotBites = new Animal("", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 1, false);
    private final static Animal dogBites = new Animal("", Animal.Type.DOG, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal dogNotBites = new Animal("", Animal.Type.DOG, Animal.Sex.M, 1, 1, 1, false);

    private static Stream<Arguments> paramsDoSpidersBiteMoreThanDogs() {
        return Stream.of(
            Arguments.of(
                null,
                false
            ),

            Arguments.of(
                List.of(),
                false
            ),

            Arguments.of(
                List.of(spiderBites),
                true
            ),

            Arguments.of(
                List.of(spiderBites, dogBites),
                false
            ),

            Arguments.of(
                List.of(dogNotBites, dogBites, spiderNotBites),
                false
            ),

            Arguments.of(
                List.of(spiderBites, spiderNotBites, spiderNotBites, dogBites, dogBites),
                false
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsDoSpidersBiteMoreThanDogs")
    void doSpidersBiteMoreThanDogsTest(List<Animal> animals, Boolean expectedResult) {
        var result = StreamApiTasks.doSpidersBiteMoreThanDogs(animals);

        assertThat(result).isEqualTo(expectedResult);
    }
}
