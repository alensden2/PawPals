package com.asdc.pawpals.controller;

import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.User;
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

  /**
   * Gets all the user records
   * @return
   */
  @GetMapping("/allUser")
  public List<User> getAllUserRecords() {
    if (adminReadService.getAllVetRecords() == null) {
      List<User> emptyEntries = null;
      return emptyEntries;
    }
    return adminReadService.getAllUserRecords();
  }
}
