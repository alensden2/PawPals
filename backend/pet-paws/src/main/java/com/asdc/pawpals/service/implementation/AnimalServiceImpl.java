package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.repository.AnimalRepository;
import com.asdc.pawpals.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;

public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalRepository animalRepository;

}
