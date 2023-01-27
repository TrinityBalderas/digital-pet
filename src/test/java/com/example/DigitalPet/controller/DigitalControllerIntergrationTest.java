package com.example.DigitalPet.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class DigitalControllerIntergrationTest {
//    @Autowired
//    MockMvc mockMvc;
//    @MockBean
//    DigitalPetController mockDigitalPetController;
//    @Test
//    public void getAllPets_ReturnPets() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/DigitalPet"))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse();
//        verify(mockDigitalPetController).getAllPets();
//    }
//}
