package edu.hw4.Validation;

import edu.hw4.Animal;

public class WeightAnimalValidator implements AnimalValidator {
    @Override
    public ValidationError validate(Animal animal) {
        if (animal.weight() < 0) {
            return new ValidationError("weight", "Weight can't be negative");
        }
        return null;
    }
}
