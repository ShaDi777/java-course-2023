package edu.hw4.Validation;

import edu.hw4.Animal;

public class AgeAnimalValidator implements AnimalValidator {
    @Override
    public ValidationError getError() {
        return new ValidationError("age", "Age can't be negative");
    }

    @Override
    public ValidationError validate(Animal animal) {
        if (animal == null) {
            return getNullPointerError();
        }

        if (animal.age() < 0) {
            return getError();
        }
        return AnimalValidator.getSuccessResult();
    }
}
