package com.asdc.pawpals.service.implementation;

import java.util.List;
import java.util.logging.LogManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asdc.pawpals.repository.AdminReadRepository;
import com.asdc.pawpals.service.AdminReadService;

import ch.qos.logback.classic.Logger;
import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.model.Animal;

@Service
public class AdminReadServiceImpl implements AdminReadService {
   //Logger logger = LogManager.getLogger(MedicalRecordServiceImpl.class);

   @Autowired
   AdminReadRepository adminReadRepository;

   public List<Animal> getAllAnimalRecords(){
    List<Animal> animalRecords = adminReadRepository.findAll();
    //logger.debug("allAnimalRecords :: retrieveAnimalRecord :: AnimalRecords are : {}", animalRecords);

    return animalRecords;

   }
}
