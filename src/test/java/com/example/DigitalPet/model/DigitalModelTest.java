package com.example.DigitalPet.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigitalModelTest {
    DigitalPetModel insideDatabase;

    @BeforeEach
    public void setup(){
        insideDatabase = new DigitalPetModel("Cali", 10, 9, 9,9);
        insideDatabase.setId(1L);
    }
    @Test
    public void isDeadSetterAndGetter(){
        DigitalPetModel input = new DigitalPetModel();
        input.setDead(false);
        assertEquals(false, input.isDead());
    }
    @Test
    public void nameSetterAndGetter(){
        DigitalPetModel input = new DigitalPetModel();
        input.setName("Zoe");
        assertEquals("Zoe", input.getName());
    }
    @Test
    public void DigitalModelToString(){
        DigitalPetModel input = new DigitalPetModel("Cali", 5, 5, 5, 5);
        String expected = "DigitalPetModel{id=null, name='Cali', isDead=false, health=5, hunger=5, energy=5, hygiene=5}";
        assertEquals(expected, input.toString());
    }
}
