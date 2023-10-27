package edu.hw4.Validation;

import edu.hw4.Animal;

public class AgeAnimalValidator implements AnimalValidator {
    @Override
    public ValidationError validate(Animal animal) {
        if (animal.age() < 0) {
            return new ValidationError("age", "Age can't be negative");
        }
        return null;
    }
}
