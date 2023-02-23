package com.asdc.pawpals.service.implementation;
import com.asdc.pawpals.dto.*;
import ch.qos.logback.classic.Logger;
import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.MedicalHistory;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.repository.AdminReadAllMedicalHistoryRepository;
import com.asdc.pawpals.repository.AdminReadAllPetOwnersRepository;
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
  AdminReadAllPetOwnersRepository adminReadAllPetOwnersRepository;

  @Autowired
  AdminReadAllVetsRepository adminReadAllVetsRepository;

  @Autowired
  AdminReadAllMedicalHistoryRepository adminReadAllMedicalHistoryRepository;

  /**
   * fetches all the animal records
   */
  public List<Animal> getAllAnimalRecords() {
    List<Animal> animalRecords = adminReadRepository.findAll();
    //logger.debug("allAnimalRecords :: retrieveAnimalRecord :: AnimalRecords are : {}", animalRecords);

    return animalRecords;
  }
  public List<PetOwner> getAllPetOwnerRecords(){
    List<PetOwner> petownerRecords = adminReadAllPetOwnersRepository.findAll();
    return petownerRecords;
  }

  public List<MedicalHistory> getAllMedicalHistoryRecords() {
    List<MedicalHistory> medicalHistory = adminReadAllMedicalHistoryRepository.findAll();
    return medicalHistory;
  }

  /**
   * fetches all the vet records
   */
  public List<Vet> getAllVetRecords() {
    List<Vet> vets = adminReadAllVetsRepository.findAll();
    return vets;
  }
 

 
}
