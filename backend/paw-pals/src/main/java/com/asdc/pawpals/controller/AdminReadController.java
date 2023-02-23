package com.asdc.pawpals.controller;

import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.MedicalHistory;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.service.AdminReadService;
import com.asdc.pawpals.service.implementation.AdminReadServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminReadController {
  // private static final Logger logger = LogManager.getLogger(AdminReadController.class);

  @Autowired
  AdminReadService adminReadService;

  /**
   * Gets all the animal records
   * @return
   */
  @GetMapping("/allAnimal")
  public List<Animal> getAllAnimalRecords() {
    if (adminReadService.getAllAnimalRecords() == null) {
      List<Animal> emptyEntries = null;
      return emptyEntries;
    }
    return adminReadService.getAllAnimalRecords();
  }

  /**
   * Gets all the pet owner records
   * @return
   */

  @GetMapping("/allPetOwner")
  public List<PetOwner> getAllPetOwnerRecords() {
    if (adminReadService.getAllPetOwnerRecords() == null) {
      List<PetOwner> emptyEntries = null;
      return emptyEntries;
    }
    return adminReadService.getAllPetOwnerRecords();
  }

  @GetMapping("/allMedicalHistory")
  public List<MedicalHistory> getAllMedicalHistoryRecords(){
    if (adminReadService.getAllMedicalHistoryRecords()== null){
      List<MedicalHistory> emptyEntries = null;
      return emptyEntries;
    }
  }
  /**
   * Gets all the vet records
   * @return
   */
  @GetMapping("/allVet")
  public List<Vet> getAllVetRecords() {
    if (adminReadService.getAllVetRecords() == null) {
      List<Vet> emptyEntries = null;
      return emptyEntries;
    }
    return adminReadService.getAllVetRecords();
  }
}
