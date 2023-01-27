package com.example.DigitalPet.responses;

public class DigitalPetResponse {
    public String message;
    public com.example.DigitalPet.model.DigitalPetModel body;

    public DigitalPetResponse(com.example.DigitalPet.model.DigitalPetModel digitalPetModel, String message){
        this.message = message;
        this.body = digitalPetModel;
    }
}
