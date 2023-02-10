package com.asdc.pawpals.controller;

import com.asdc.pawpals.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalController {

    @Autowired()
    AnimalService animalService;


}
