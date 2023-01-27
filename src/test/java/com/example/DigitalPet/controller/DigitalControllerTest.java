package com.example.DigitalPet.controller;

import com.example.DigitalPet.eception.DigitalPetNameAlreadyExists;
import com.example.DigitalPet.eception.DigitalPetNotFoundException;
import com.example.DigitalPet.model.DigitalPetModel;
import com.example.DigitalPet.responses.DigitalPetResponse;
import com.example.DigitalPet.service.DigitalPetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DigitalControllerTest {
    private DigitalPetService mockDigitalService;
    private DigitalPetModel insideDatabase;
    DigitalPetController digitalPetController;

    @BeforeEach
    public void setup(){
        insideDatabase = new DigitalPetModel("Cali", 10, 9, 9, 9);
        insideDatabase.setId(1L);
        mockDigitalService = mock(DigitalPetService.class);
        digitalPetController = new DigitalPetController(mockDigitalService);
    }
    @Test
    public void getAll_CallsService(){
        digitalPetController.getAllPets();
        verify(mockDigitalService).getAllPets();
    }
    @Test
    public void getAll_ReturnValueService(){
        when(mockDigitalService.getAllPets()).thenReturn(null);
        assertEquals(null, digitalPetController.getAllPets());
    }
    @Test
    public void getPetById_ShouldReturn404WhenRecordDoesNotExist(){
        Mockito.when(mockDigitalService.getPetById(1L)).thenThrow(new DigitalPetNotFoundException("Pet 1 not found"));
        ResponseEntity<DigitalPetModel> actual = digitalPetController.getPetById(1L);
        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }
    @Test
    public void createPet_ShouldReturn400WhenNameAlreadyExists(){
        DigitalPetModel input = new DigitalPetModel("Cali", 10, 9, 9, 9);
        Mockito.when(mockDigitalService.createDigitalPet(input)).thenThrow(new DigitalPetNameAlreadyExists("Cali already exists"));
        ResponseEntity<DigitalPetModel> actual = digitalPetController.createPet(input);
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }
    @Test
    public void updatePet_ShouldReturn404WhenRecordDoesNotExist(){
        DigitalPetModel input = new DigitalPetModel();
        Mockito.when(mockDigitalService.updateDigitalPet(2L, input)).thenThrow(new DigitalPetNotFoundException("Cali 2 not found"));

        ResponseEntity<DigitalPetModel> actual = digitalPetController.updatePet(2L, input);

        assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
    }
    @Test
    public void deletePet_isDeletedByService(){
        digitalPetController.deletePet(2L);
        Mockito.verify(mockDigitalService).deleteDigitalPet(2L);
    }
    @Test
    public void deletePet_TaskCompletedMessage(){
        DigitalPetModel digitalPetModel = new DigitalPetModel();
        ResponseEntity<DigitalPetResponse> actual = digitalPetController.deletePet(1L);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals("Pet 1 was deleted.", actual.getBody().message);
    }
}
