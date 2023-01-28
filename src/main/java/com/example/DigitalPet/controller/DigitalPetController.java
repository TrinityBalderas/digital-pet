package com.example.DigitalPet.controller;

import com.example.DigitalPet.eception.DigitalPetNameAlreadyExists;
import com.example.DigitalPet.eception.DigitalPetNotFoundException;
import com.example.DigitalPet.model.DigitalPetModel;
import com.example.DigitalPet.responses.DigitalPetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("DigitalPet")
public class DigitalPetController {

    private com.example.DigitalPet.service.DigitalPetService digitalPetService;
    @Autowired
    public DigitalPetController(com.example.DigitalPet.service.DigitalPetService digitalPetService) {
        this.digitalPetService = digitalPetService;
    }
    @GetMapping
    public List<DigitalPetModel> getAllPets() {
        return digitalPetService.getAllPets();
    }
    @GetMapping("{id}")
    public ResponseEntity<DigitalPetModel> getPetById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(digitalPetService.getPetById(id));
        } catch (DigitalPetNotFoundException e) {
            return new ResponseEntity(new DigitalPetResponse(null, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<DigitalPetModel> createPet(@RequestBody DigitalPetModel body){
        try{
            return ResponseEntity.ok(digitalPetService.createDigitalPet(body));
        } catch (DigitalPetNameAlreadyExists e){
            return new ResponseEntity(new DigitalPetResponse(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("{id}")
    public ResponseEntity<DigitalPetModel> updatePet(@PathVariable Long id, @RequestBody DigitalPetModel body){
        try{
            return ResponseEntity.ok(digitalPetService.updateDigitalPet(id, body));
        } catch (DigitalPetNotFoundException e) {
            return new ResponseEntity(new DigitalPetResponse(null, e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<DigitalPetResponse> deletePet(@PathVariable Long id){
        digitalPetService.deleteDigitalPet(id);
        return ResponseEntity.ok(new DigitalPetResponse(null, "Pet " + id + " was deleted."));
    }
}
