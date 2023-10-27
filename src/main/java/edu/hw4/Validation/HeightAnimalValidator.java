package edu.hw4.Validation;

import edu.hw4.Animal;

public class HeightAnimalValidator implements AnimalValidator {
    @Override
    public ValidationError validate(Animal animal) {
        if (animal.height() < 0) {
            return new ValidationError("height", "Height can't be negative");
        }
        return null;
    }
}
