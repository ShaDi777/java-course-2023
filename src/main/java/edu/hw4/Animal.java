package edu.hw4;

import edu.hw4.Validation.AnimalValidator;
import edu.hw4.Validation.ValidationError;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    public enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    public enum Sex {
        M, F
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public int paws() {
        return switch (type) {
            case CAT, DOG -> 4;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> 8;
        };
    }

    public Set<ValidationError> doValidation(Collection<AnimalValidator> collection) {
        Set<ValidationError> set = new HashSet<>();
        for (var validator : collection) {
            ValidationError error = validator.validate(this);
            if (error != null) {
                set.add(error);
            }
        }
        return set;
    }
}
