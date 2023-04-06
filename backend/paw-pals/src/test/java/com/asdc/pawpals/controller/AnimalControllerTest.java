package com.asdc.pawpals.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.asdc.pawpals.service.AnimalService;
import org.junit.Test;
import com.asdc.pawpals.service.implementation.AnimalServiceImpl;



@RunWith(MockitoJUnitRunner.class)
public class AnimalControllerTest {

    @Mock
    AnimalServiceImpl animalServiceMock;

    @InjectMocks
    AnimalController animalController;

    @Mock
    ApiResponse apiResponseMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void objectCreated() {
        assertNotNull(animalController);
        assertNotNull(animalController);
        assertNotNull(animalController);
    }
    
}
