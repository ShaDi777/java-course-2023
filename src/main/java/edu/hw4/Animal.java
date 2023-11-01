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
        CAT(4),
        DOG(4),
        BIRD(2),
        FISH(0),
        SPIDER(8);

        private final int paws;

        Type(int paws) {
            this.paws = paws;
        }

        int getPaws() {
            return paws;
        }
    }

    public enum Sex {
        M, F
    }

    public int paws() {
        return type.getPaws();
    }

    public Set<ValidationError> doValidation(Collection<AnimalValidator> collection) {
        Set<ValidationError> set = new HashSet<>();
        for (var validator : collection) {
            ValidationError error = validator.validate(this);
            if (!error.equals(AnimalValidator.getSuccessResult())) {
                set.add(error);
            }
        }
        return set;
    }
}
