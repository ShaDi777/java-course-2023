package edu.hw4.Validation;

import edu.hw4.Animal;

public class NameAnimalValidator implements AnimalValidator {
    @Override
    public ValidationError validate(Animal animal) {
        if (animal.name().isBlank() || animal.name().isEmpty()) {
            return new ValidationError("name", "Name can't be empty");
        }
        return null;
    }
}
