package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.service.AdminReadService;

import java.util.List;

import com.asdc.pawpals.utils.CommonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
  // private static final Logger logger = LogManager.getLogger(AdminController.class);
  private static final Logger logger = LogManager.getLogger(VetController.class);


  @Autowired
  AdminReadService adminReadService;

  /**
   * Gets all the animal records
   * @return
   */
  @GetMapping("/all-animals")
  public List<Animal> getAllAnimalRecords() {
    if (adminReadService.getAllAnimalRecords() == null) {
      List<Animal> emptyEntries = null;
      return emptyEntries;
    }
    return adminReadService.getAllAnimalRecords();
  }

  /**
   * Gets all the vet records
   * @return
   */
  @GetMapping("/all-vets")
  public List<Vet> getAllVetRecords() {
    if (adminReadService.getAllVetRecords() == null) {
      List<Vet> emptyEntries = null;
      return emptyEntries;
    }
    return adminReadService.getAllVetRecords();
  }

  /**
   * Gets all the user records
   * @return
   */
  @GetMapping("/all-users")
  public List<User> getAllUserRecords() {
    if (adminReadService.getAllVetRecords() == null) {
      List<User> emptyEntries = null;
      return emptyEntries;
    }
    return adminReadService.getAllUserRecords();
  }

//  @PostMapping("/add-vet")
//  public ResponseEntity<Boolean> addVet(@ResponseBody Object requestBody){
//    logger.info("Recieved request as :", requestBody.toString());
//    /** add edge case if wrong data sent */
//    boolean vetAdded = false;
//
//    if(CommonUtils.isStrictTypeOf(requestBody, VetDto.class)){
//      VetDto vetDto = Objec
//    }
//    return ResponseEntity.ok(vetAdded);
//  }
}
