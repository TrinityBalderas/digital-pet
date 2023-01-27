package com.example.DigitalPet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DigitalPetRepository extends JpaRepository<com.example.DigitalPet.model.DigitalPetModel, Long> {
   boolean existsByNameIgnoreCase(String name);
}
