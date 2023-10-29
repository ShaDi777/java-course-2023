package edu.hw4.Validation;

import edu.hw4.Animal;

public class HeightAnimalValidator implements AnimalValidator {
    @Override
    public ValidationError getError() {
        return new ValidationError("height", "Height can't be negative");
    }

    @Override
    public ValidationError validate(Animal animal) {
        if (animal == null) {
            return null;
        }

        if (animal.height() < 0) {
            return getError();
        }
        return null;
    }
}
