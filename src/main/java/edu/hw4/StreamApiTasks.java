package edu.hw4;

import edu.hw4.Validation.AnimalValidator;
import edu.hw4.Validation.ValidationError;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("checkstyle:MethodName")
public final class StreamApiTasks {
    private StreamApiTasks() {
    }

    public static List<Animal> task1_SortByHeightAsc(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::height))
            .toList();
    }

    public static List<Animal> task2_SortByWeightDescAndChooseFirstK(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public static Map<Animal.Type, Long> task3_TypeCountMap(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.counting()
                )
            );
    }

    public static Animal task4_FindAnimalWithLongestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(Animal::name))
            .orElse(null);
    }

    public static Animal.Sex task5_WhichAnimalsMore(List<Animal> animals) {
        long males = animals.stream().filter(animal -> animal.sex() == Animal.Sex.M).count();
        long females = animals.size() - males;
        return males > females ? Animal.Sex.M : Animal.Sex.F;
    }

    public static Map<Animal.Type, Animal> task6_TypeMaxWeightAnimalMap(List<Animal> animals) {
        return animals.stream()
            .collect(
                Collectors.toMap(
                    Animal::type,
                    Function.identity(),
                    BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
                )
            );
    }

    public static Animal task7_KthEldestAnimal(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .skip(k - 1).findFirst().orElse(null);
    }

    public static Optional<Animal> task8_MaxWeightedAnimalAmongLowerThanKcm(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }

    public static Integer task9_TotalPaws(List<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> task10_ListAnimalsAgeNotEqualsPaws(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.age() != animal.paws()).toList();
    }

    private static final int BIG_SIZE = 100;

    public static List<Animal> task11_ListAnimalsBigAndBites(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.bites() && animal.height() > BIG_SIZE).toList();
    }

    public static Long task12_CountAnimalWeightMoreThanHeight(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.weight() > animal.height()).count();
    }

    public static List<Animal> task13_ListAnimalsNameHasMoreThanTwoWords(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.name().split(" ").length > 2).toList();
    }

    public static Boolean task14_ContainsDogWithHigherThanKcm(List<Animal> animals, int k) {
        return animals.stream().anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public static Integer task15_TotalWeightOfAnimalsAgedFromKToL(List<Animal> animals, int k, int l) {
        return animals.stream().filter(animal -> k <= animal.age() && animal.age() <= l).mapToInt(Animal::weight).sum();
    }

    public static List<Animal> task16_SortByTypeSexName(List<Animal> animals) {
        return animals.stream()
            .sorted(
                Comparator.comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name)
            ).toList();
    }

    public static Boolean task17_DoSpidersBiteMoreThanDogs(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites()).count()
            > animals.stream().filter(animal -> animal.type() == Animal.Type.DOG && animal.bites()).count();
    }

    // TODO test
    @SafeVarargs
    public static Animal task18_todo(
        List<Animal> firstList,
        List<Animal> secondList,
        List<Animal>... otherLists
    ) {
        Stream<Animal> firstStream = firstList.stream();
        Stream<Animal> secondStream = secondList.stream();
        Stream<Animal> otherStream = Arrays.stream(otherLists).flatMap(Collection::stream);
        Stream<Animal> stream = Stream.concat(Stream.concat(firstStream, secondStream), otherStream);

        return stream.filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight)).orElse(null);
    }

    // TODO test


    public static Animal task18_HeaviestFishInTwoOrMoreLists(List<List<Animal>> animalsLists) {
        return animalsLists.stream()
            .map(list -> list.stream()
                .filter(animal -> animal.type() == Animal.Type.FISH)
                .max(Comparator.comparing(Animal::weight)).orElse(null)
            ).filter(Objects::nonNull)
            .max(Comparator.comparing(Animal::weight)).orElse(null);
    }

    public static Map<String, Set<ValidationError>> task19_AnimalsWithErrors(
        List<Animal> animals,
        List<AnimalValidator> validators
    ) {
        return animals.stream()
            .filter(animal -> !animal.doValidation(validators).isEmpty())
            .collect(
                Collectors.toMap(
                    Animal::name,
                    animal -> animal.doValidation(validators)
                )
            );
    }

    public static Map<String, String> task20_AnimalsWithErrorFields(
        List<Animal> animals,
        List<AnimalValidator> validators
    ) {
        return task19_AnimalsWithErrors(animals, validators)
            .entrySet().stream().map(entry ->
                Map.entry(
                    entry.getKey(),
                    entry.getValue().stream().map(ValidationError::fieldName)
                        .collect(Collectors.joining(", "))
                )
            ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
