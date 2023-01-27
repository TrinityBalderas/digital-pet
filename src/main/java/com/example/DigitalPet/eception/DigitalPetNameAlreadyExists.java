package com.example.DigitalPet.eception;

public class DigitalPetNameAlreadyExists extends RuntimeException{
    public DigitalPetNameAlreadyExists(String message){
        super(message);
    }
}
