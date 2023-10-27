package edu.hw4.Validation;

import edu.hw4.Animal;

public interface AnimalValidator {
    ValidationError validate(Animal animal);
}
