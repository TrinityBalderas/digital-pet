package com.example.DigitalPet.eception;

public class DigitalPetNotFoundException extends RuntimeException {
    public DigitalPetNotFoundException(String message){
        super(message);
    }
}
