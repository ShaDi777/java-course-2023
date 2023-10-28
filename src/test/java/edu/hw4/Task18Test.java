package edu.hw4;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task18Test {
    private final static Animal cat = new Animal("", Animal.Type.CAT, Animal.Sex.M, 1, 1, 999, true);
    private final static Animal dog = new Animal("", Animal.Type.DOG, Animal.Sex.M, 1, 1, 999, true);
    private final static Animal bird = new Animal("", Animal.Type.BIRD, Animal.Sex.M, 1, 1, 999, true);
    private final static Animal spider = new Animal("", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 999, true);
    private final static Animal fish1kg = new Animal("", Animal.Type.FISH, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal fish10kg = new Animal("", Animal.Type.FISH, Animal.Sex.M, 1, 1, 10, true);
    private final static Animal fish15kg = new Animal("", Animal.Type.FISH, Animal.Sex.M, 1, 1, 15, true);

    private static Stream<Arguments> paramsHeaviestFishInTwoOrMoreLists() {
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
                List.of(
                    List.of(cat, dog),
                    List.of(cat, dog, fish1kg),
                    List.of(bird, spider)
                ),
                fish1kg
            ),

            Arguments.of(
                List.of(
                    List.of(cat, dog, fish10kg),
                    List.of(cat, dog, fish1kg),
                    List.of(bird, spider, fish15kg)
                ),
                fish15kg
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsHeaviestFishInTwoOrMoreLists")
    void heaviestFishInTwoOrMoreListsTest(List<List<Animal>> animalsLists, Animal expectedResult) {
        var result = StreamApiTasks.task18_HeaviestFishInTwoOrMoreLists(animalsLists);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void heaviestFishVarargsTest() {
        List<Animal> list1 = List.of();
        List<Animal> list2 = List.of(cat, dog);
        List<Animal> list3 = List.of(fish1kg, fish10kg);
        List<Animal> list4 = List.of(spider, fish15kg, bird);
        List<Animal> list5 = List.of(fish15kg, fish15kg, fish15kg);
        List<Animal> list6 = List.of(cat, dog, spider);

        var result = StreamApiTasks.task18_HeaviestFish(list1, list2, list3, list4, list5, list6);

        assertThat(result).isEqualTo(fish15kg);
    }
}
