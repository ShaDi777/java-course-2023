package edu.hw4;

import edu.hw4.Validation.AgeAnimalValidator;
import edu.hw4.Validation.AnimalValidator;
import edu.hw4.Validation.HeightAnimalValidator;
import edu.hw4.Validation.NameAnimalValidator;
import edu.hw4.Validation.ValidationError;
import edu.hw4.Validation.WeightAnimalValidator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task19Test {
    private final static AnimalValidator ageValidator = new AgeAnimalValidator();
    private final static AnimalValidator heightValidator = new HeightAnimalValidator();
    private final static AnimalValidator nameValidator = new NameAnimalValidator();
    private final static AnimalValidator weightValidator = new WeightAnimalValidator();
    private final static List<AnimalValidator> validators = List.of(
        ageValidator, heightValidator, nameValidator, weightValidator
    );

    private final static Animal errorName = new Animal("", Animal.Type.CAT, Animal.Sex.M, 1, 1, 1, true);
    private final static Animal errorAge =
        new Animal("ageErrorAnimal", Animal.Type.CAT, Animal.Sex.M, -100, 1, 1, true);
    private final static Animal errorHeight =
        new Animal("heightErrorAnimal", Animal.Type.DOG, Animal.Sex.M, 1, -100, 1, true);
    private final static Animal errorWeight =
        new Animal("weightErrorAnimal", Animal.Type.BIRD, Animal.Sex.M, 1, 1, -100, true);
    private final static Animal errorAgeHeightWeight =
        new Animal("fullErrorAnimal", Animal.Type.SPIDER, Animal.Sex.M, -10, -10, -10, true);

    private static Stream<Arguments> paramsAnimalsWithErrors() {
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
                List.of(errorName, errorAge, errorHeight, errorWeight),
                Map.of(
                    "", Set.of(nameValidator.getError()),
                    "ageErrorAnimal", Set.of(ageValidator.getError()),
                    "heightErrorAnimal", Set.of(heightValidator.getError()),
                    "weightErrorAnimal", Set.of(weightValidator.getError())
                )
            ),

            Arguments.of(
                List.of(errorAgeHeightWeight),
                Map.of(
                    "fullErrorAnimal",
                    Set.of(
                        ageValidator.getError(),
                        heightValidator.getError(),
                        weightValidator.getError()
                    )
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource("paramsAnimalsWithErrors")
    void animalsWithErrorsTest(List<Animal> animals, Map<String, Set<ValidationError>> expectedResult) {
        var result = StreamApiTasks.task19_AnimalsWithErrors(animals, validators);

        assertThat(result).isEqualTo(expectedResult);
    }
}
