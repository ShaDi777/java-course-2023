package edu.hw4;

import edu.hw4.Validation.AnimalValidator;
import edu.hw4.Validation.ValidationError;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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

    public static List<Animal> sortByHeightAsc(List<Animal> animals) {
        if (animals == null) {
            return List.of();
        }

        return animals.stream()
            .sorted(Comparator.comparing(Animal::height))
            .toList();
    }

    public static List<Animal> sortByWeightDescAndChooseFirstK(List<Animal> animals, int k) {
        if (animals == null) {
            return List.of();
        }

        return animals.stream()
            .sorted(Comparator.comparing(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public static Map<Animal.Type, Integer> typeFrequencyMap(List<Animal> animals) {
        if (animals == null) {
            return Map.of();
        }

        return animals.stream()
            .collect(
                Collectors.groupingBy(
                    Animal::type,
                    Collectors.reducing(0, e -> 1, Integer::sum)
                )
            );
    }

    public static Animal findAnimalWithLongestName(List<Animal> animals) {
        if (animals == null) {
            throw new NoSuchElementException();
        }

        return animals.stream()
            .max(Comparator.comparing(Animal::name, Comparator.comparingInt(String::length)))
            .orElseThrow(NoSuchElementException::new);
    }

    public static Animal.Sex findWhichAnimalsMore(List<Animal> animals) {
        if (animals == null) {
            return null;
        }

        long males = animals.stream().filter(animal -> animal.sex() == Animal.Sex.M).count();
        long females = animals.size() - males;
        return males > females
            ? Animal.Sex.M
            : males == females ? null : Animal.Sex.F;
    }

    public static Map<Animal.Type, Animal> typeMaxWeightAnimalMap(List<Animal> animals) {
        if (animals == null) {
            return Map.of();
        }

        return animals.stream()
            .collect(
                Collectors.toMap(
                    Animal::type,
                    Function.identity(),
                    BinaryOperator.maxBy(Comparator.comparing(Animal::weight))
                )
            );
    }

    public static Animal kthEldestAnimal(List<Animal> animals, int k) {
        if (animals == null) {
            throw new NoSuchElementException();
        }

        return animals.stream()
            .sorted(Comparator.comparing(Animal::age).reversed())
            .skip(k - 1).findFirst().orElseThrow(NoSuchElementException::new);
    }

    public static Optional<Animal> maxWeightedAnimalAmongLowerThanKcm(List<Animal> animals, int k) {
        if (animals == null) {
            return Optional.empty();
        }

        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparing(Animal::weight));
    }

    public static Integer getTotalPaws(List<Animal> animals) {
        if (animals == null) {
            return 0;
        }

        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> getListAnimalsAgeNotEqualsPaws(List<Animal> animals) {
        if (animals == null) {
            return List.of();
        }

        return animals.stream().filter(animal -> animal.age() != animal.paws()).toList();
    }

    private static final int BIG_SIZE = 100;

    public static List<Animal> getListAnimalsBigAndBites(List<Animal> animals) {
        if (animals == null) {
            return List.of();
        }

        return animals.stream().filter(animal -> animal.bites() && animal.height() > BIG_SIZE).toList();
    }

    public static Long countAnimalWeightMoreThanHeight(List<Animal> animals) {
        if (animals == null) {
            return 0L;
        }

        return animals.stream().filter(animal -> animal.weight() > animal.height()).count();
    }

    public static List<Animal> getListAnimalsNameHasMoreThanTwoWords(List<Animal> animals) {
        if (animals == null) {
            return List.of();
        }

        return animals.stream().filter(animal -> animal.name().split(" ").length > 2).toList();
    }

    public static Boolean containsDogWithHigherThanKcm(List<Animal> animals, int k) {
        if (animals == null) {
            return false;
        }

        return animals.stream().anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    public static Integer getTotalWeightOfAnimalsAgedFromKToL(List<Animal> animals, int k, int l) {
        if (animals == null) {
            return 0;
        }

        return animals.stream().filter(animal -> k <= animal.age() && animal.age() <= l).mapToInt(Animal::weight).sum();
    }

    public static List<Animal> sortByTypeSexName(List<Animal> animals) {
        if (animals == null) {
            return List.of();
        }

        return animals.stream()
            .sorted(
                Comparator.comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name)
            ).toList();
    }

    public static Boolean doSpidersBiteMoreThanDogs(List<Animal> animals) {
        if (animals == null) {
            return false;
        }

        return animals.stream().filter(animal -> animal.type() == Animal.Type.SPIDER && animal.bites()).count()
            > animals.stream().filter(animal -> animal.type() == Animal.Type.DOG && animal.bites()).count();
    }

    @SafeVarargs
    public static Animal getHeaviestFish(
        List<Animal> firstList,
        List<Animal> secondList,
        List<Animal>... otherLists
    ) {
        Stream<Animal> firstStream = firstList.stream();
        Stream<Animal> secondStream = secondList.stream();
        Stream<Animal> otherStream = Arrays.stream(otherLists).flatMap(Collection::stream);
        Stream<Animal> stream = Stream.concat(Stream.concat(firstStream, secondStream), otherStream);

        return stream.filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .orElseThrow(NoSuchElementException::new);
    }

    public static Animal getHeaviestFishInTwoOrMoreLists(List<List<Animal>> animalsLists) {
        if (animalsLists == null) {
            throw new NoSuchElementException();
        }

        return animalsLists.stream()
            .map(list -> list.stream()
                .filter(animal -> animal.type() == Animal.Type.FISH)
                .max(Comparator.comparing(Animal::weight)).orElse(null)
            ).filter(Objects::nonNull)
            .max(Comparator.comparing(Animal::weight))
            .orElseThrow(NoSuchElementException::new);
    }

    public static Map<String, Set<ValidationError>> mapAnimalsWithErrors(
        List<Animal> animals,
        List<AnimalValidator> validators
    ) {
        if (animals == null) {
            return Map.of();
        }

        return animals.stream()
            .filter(animal -> !animal.doValidation(validators).isEmpty())
            .collect(
                Collectors.toMap(
                    Animal::name,
                    animal -> animal.doValidation(validators)
                )
            );
    }

    public static Map<String, String> mapAnimalsWithErrorFields(
        List<Animal> animals,
        List<AnimalValidator> validators
    ) {
        if (animals == null) {
            return Map.of();
        }

        return mapAnimalsWithErrors(animals, validators)
            .entrySet().stream().map(entry ->
                Map.entry(
                    entry.getKey(),
                    entry.getValue().stream().map(ValidationError::fieldName)
                        .collect(Collectors.joining(", "))
                )
            ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
