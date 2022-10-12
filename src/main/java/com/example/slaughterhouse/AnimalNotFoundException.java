package com.example.slaughterhouse;

public class AnimalNotFoundException extends RuntimeException {
    AnimalNotFoundException(Long registrationNumber) {
        super("Could not find animal, with registration number: " + registrationNumber);
    }
}
