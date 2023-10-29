package edu.hw4.Validation;

import edu.hw4.Animal;

public class WeightAnimalValidator implements AnimalValidator {
    @Override
    public ValidationError getError() {
        return new ValidationError("weight", "Weight can't be negative");
    }

    @Override
    public ValidationError validate(Animal animal) {
        if (animal == null) {
            return null;
        }

        if (animal.weight() < 0) {
            return getError();
        }
        return null;
    }
}
