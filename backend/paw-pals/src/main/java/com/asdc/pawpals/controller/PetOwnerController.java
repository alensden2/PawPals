package com.asdc.pawpals.controller;

import com.asdc.pawpals.service.PetOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetOwnerController {

    @Autowired
    PetOwnerService petOwnerService;
    //@GetMapping("/petowners")
    //public List<PetOwnerDto> getAllPetOwners() {
  //      List<PetOwner> pawpals = PetOwnerService.getAllPetOwners();
   //     return UserMapper.toUserDtos(pawpals);
    //}
}
