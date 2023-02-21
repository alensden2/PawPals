package com.asdc.pawpals.service.implementation;

import ch.qos.logback.classic.Logger;
import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.repository.AdminReadAllVetsRepository;
import com.asdc.pawpals.repository.AdminReadRepository;
import com.asdc.pawpals.service.AdminReadService;
import java.util.List;
import java.util.logging.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Service;

@Service
public class AdminReadServiceImpl implements AdminReadService {
  //Logger logger = LogManager.getLogger(MedicalRecordServiceImpl.class);

  @Autowired
  AdminReadRepository adminReadRepository;

  @Autowired
  AdminReadAllVetsRepository adminReadAllVetsRepository;

  /**
   * fetches all the animal records
   */
  public List<Animal> getAllAnimalRecords() {
    List<Animal> animalRecords = adminReadRepository.findAll();
    //logger.debug("allAnimalRecords :: retrieveAnimalRecord :: AnimalRecords are : {}", animalRecords);

    return animalRecords;
  }

  /**
   * fetches all the vet records
   */
  public List<Vet> getAllVetRecords() {
    List<Vet> vets = adminReadAllVetsRepository.findAll();
    return vets;
  }
}
