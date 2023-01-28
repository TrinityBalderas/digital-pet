package com.example.DigitalPet.service;

import com.example.DigitalPet.eception.DigitalPetNameAlreadyExists;
import com.example.DigitalPet.eception.DigitalPetNotFoundException;
import com.example.DigitalPet.model.DigitalPetModel;
import com.example.DigitalPet.repository.DigitalPetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

public class DigitalServiceTest {
    private DigitalPetRepository mockDigitalPetRepository;
    private DigitalPetModel insideDatabase;
    private DigitalPetService digitalPetService;

    @BeforeEach
    public void setup(){
        insideDatabase = new DigitalPetModel("Cali", 10, 9, 9, 9);
        insideDatabase.setId(1L);
        mockDigitalPetRepository = mock(DigitalPetRepository.class);
        digitalPetService = new DigitalPetService(mockDigitalPetRepository);
        when(mockDigitalPetRepository.findById(1L)).thenReturn(Optional.of(insideDatabase));
        when(mockDigitalPetRepository.save(any())).thenAnswer(method -> method.getArgument(0));
    }
    @Test
    public void getAll_CallsRepo(){
        digitalPetService.getAllPets();
        Mockito.verify(mockDigitalPetRepository).findAll();
    }
    @Test
    public void getById_IfSearchResultIdEmptyThenReturnError(){
        DigitalPetNotFoundException exception = assertThrows(DigitalPetNotFoundException.class, () -> {
            digitalPetService.getPetById(2L);
                });
        assertEquals("Digital Pet 2 not found", exception.getMessage());
    }
    @Test
    public void getById_GetByRepo() {
        Mockito.when(mockDigitalPetRepository.findById(1L)).thenReturn(Optional.of(insideDatabase));
        DigitalPetModel actual = digitalPetService.getPetById(1L);
        assertEquals(1L, actual.getId());
    }
    @Test
    public void create_ShouldThrowErrorIfNameAlreadyEcists(){
        DigitalPetModel body = new DigitalPetModel("Cali", null,null,null,null);
        DigitalPetNameAlreadyExists exception = assertThrows(DigitalPetNameAlreadyExists.class, () -> {
            Mockito.when(mockDigitalPetRepository.existsByNameIgnoreCase(body.getName())).thenReturn(true);
            digitalPetService.createDigitalPet(body);
        });
        assertEquals("Cali already exists", exception.getMessage());
    }
    @Test
    public void Create_isCreatedByRepo(){
        DigitalPetModel input = new DigitalPetModel("Bob", null,null,null,null);
        Mockito.when(mockDigitalPetRepository.existsByNameIgnoreCase(input.getName())).thenReturn(false);
        digitalPetService.createDigitalPet(input);
        Mockito.verify(mockDigitalPetRepository).save(any());
    }
    @Test
    public void update_ShouldOnlyUppdateHealth(){
        DigitalPetModel input = new DigitalPetModel();
        input.setHealth(8);
        DigitalPetModel actual = digitalPetService.updateDigitalPet(1L, input);
        Mockito.verify(mockDigitalPetRepository).save(any());
        assertEquals(8, actual.getHealth());
    }
    @Test
    public void update_ShouldOnlyUpdateHunger(){
        DigitalPetModel input = new DigitalPetModel();
        input.setHunger(8);
        DigitalPetModel actual = digitalPetService.updateDigitalPet(1L, input);
        Mockito.verify(mockDigitalPetRepository).save(any());
        assertEquals(8, actual.getHunger());
    }
    @Test
    public void update_ShouldOnlyUpdateEnergy(){
        DigitalPetModel input = new DigitalPetModel();
        input.setEnergy(8);
        DigitalPetModel actual = digitalPetService.updateDigitalPet(1L, input);
        Mockito.verify(mockDigitalPetRepository).save(any());
        assertEquals(8, actual.getEnergy());
    }
    @Test
    public void update_shouldOnlyUpdateHygiene(){
        DigitalPetModel input = new DigitalPetModel();
        input.setHygiene(8);
        DigitalPetModel actual = digitalPetService.updateDigitalPet(1L, input);
        Mockito.verify(mockDigitalPetRepository).save(any());
        assertEquals(insideDatabase, actual);
    }
    @Test
    public void update_ShouldUpdateIfNotNull(){
        DigitalPetModel input = new DigitalPetModel("Zoe",null,null,null,null);
        DigitalPetModel actual = digitalPetService.updateDigitalPet(1L, input);
        Mockito.verify(mockDigitalPetRepository).save(any());
        assertEquals(insideDatabase, actual);
    }
    @Test
    public void delete_IsDeleteByRepo(){
        Mockito.when(mockDigitalPetRepository.existsById(1L)).thenReturn(true);
        digitalPetService.deleteDigitalPet(1L);
        Mockito.verify(mockDigitalPetRepository).deleteById(1L);
    }
    @Test
    public void delete_IfInputDoesNotExistThenDoesNothing(){
        Mockito.when(mockDigitalPetRepository.existsById(2L)).thenReturn(false);
        digitalPetService.deleteDigitalPet(2L);
        Mockito.verify(mockDigitalPetRepository, times(0)).deleteById(2L);
    }
}
