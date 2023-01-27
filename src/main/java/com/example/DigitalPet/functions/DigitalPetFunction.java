package com.example.DigitalPet.functions;

import com.example.DigitalPet.eception.DigitalPetNameAlreadyExists;
import com.example.DigitalPet.eception.DigitalPetNotFoundException;
import com.example.DigitalPet.model.DigitalPetModel;
import com.example.DigitalPet.responses.DigitalPetResponse;
import com.example.DigitalPet.service.DigitalPetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.beans.BeanProperty;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class DigitalPetFunction {
    private final DigitalPetService digitalPetService;
    public DigitalPetFunction(DigitalPetService digitalPetService){
        this.digitalPetService = digitalPetService;
    }
    @Bean
    public Supplier<List<DigitalPetModel>> getAllPets(){
        return () -> digitalPetService.getAllPets();
    }
    @Bean
    public Supplier<ResponseEntity<DigitalPetModel>> getById(Long id){
        try {
            return () -> ResponseEntity.ok(digitalPetService.getPetById(id));
        } catch (DigitalPetNotFoundException e){
            return () -> new ResponseEntity(new DigitalPetResponse(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @Bean
    public Consumer<ResponseEntity<DigitalPetModel>> createPet(@RequestBody DigitalPetModel body){
        try{
            return (DigitalPetModel) -> digitalPetService.createDigitalPet(body);
        } catch (DigitalPetNameAlreadyExists e) {
            return (digitalPetModelResponseEntity) -> new ResponseEntity(new DigitalPetResponse(null, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

}
