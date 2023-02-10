package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.repository.AnimalRepository;
import com.asdc.pawpals.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalRepository animalRepository;

}
