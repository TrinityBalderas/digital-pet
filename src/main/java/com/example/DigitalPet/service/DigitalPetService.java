package com.example.DigitalPet.service;

import com.example.DigitalPet.eception.DigitalPetNameAlreadyExists;
import com.example.DigitalPet.eception.DigitalPetNotFoundException;
import com.example.DigitalPet.model.DigitalPetModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DigitalPetService {

    private final com.example.DigitalPet.repository.DigitalPetRepository digitalPetRepository;

    public DigitalPetService(com.example.DigitalPet.repository.DigitalPetRepository digitalPetRepository) {
        this.digitalPetRepository = digitalPetRepository;
    }

    public List<DigitalPetModel> getAllPets() {
        return digitalPetRepository.findAll();
    }
    public DigitalPetModel getPetById(Long id){
        Optional<DigitalPetModel> searchResult = digitalPetRepository.findById(id);
        if(searchResult.isEmpty()){
            throw new DigitalPetNotFoundException("Digital Pet " + id + " not found");
        }
        return searchResult.get();
    }
    public DigitalPetModel createDigitalPet(DigitalPetModel body){
        if(digitalPetRepository.existsByNameIgnoreCase(body.getName())){
            throw new DigitalPetNameAlreadyExists(body.getName() + " already exists");
        }
        DigitalPetModel digitalPet = new DigitalPetModel(body.getName(),10, 9, 9,9);
        DigitalPetModel saved = digitalPetRepository.save(digitalPet);
        return saved;
    }
    public DigitalPetModel updateDigitalPet(Long id, DigitalPetModel body){
        body.setId(id);
        DigitalPetModel original = digitalPetRepository.findById(id).get();
        if (body.getHealth() != null) {
            original.setHealth(body.getHealth());
        }
        if (body.getHunger() != null) {
            original.setHunger(body.getHunger());
        }
        if (body.getEnergy() != null) {
            original.setEnergy(body.getEnergy());
        }
        if (body.getHygiene() != null) {
            original.setHygiene(body.getHygiene());
        }
        return digitalPetRepository.save(original);
    }
    public void deleteDigitalPet(Long id) {
        if(digitalPetRepository.existsById(id)){
            digitalPetRepository.deleteById(id);
        }
    }
}
