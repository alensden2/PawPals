package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.service.AdminReadService;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.ObjectMapperWrapper;
import java.util.List;
import org.springframework.http.HttpStatus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("unauth/admin")
public class AdminController {
  // private static final Logger logger = LogManager.getLogger(AdminController.class);
  private static final Logger logger = LogManager.getLogger(
    AdminController.class
  );

  @Autowired
  AdminReadService adminReadService;

  @Autowired
  ApiResponse apiResponse;

  /**
   * Gets all the animal records
   *
   * @return
   * tbr
   */
  @GetMapping("/all-animals")
  public ResponseEntity<List<AnimalDto>> getAllAnimalRecords() {
    List<AnimalDto> animalDetails = null;
    if (adminReadService != null) {
      animalDetails = adminReadService.getAllAnimalRecords();
    }
    return ResponseEntity.ok().body(animalDetails);
  }

  /**
   * Gets all the vet records
   *
   * @return
   */
  @GetMapping("/all-vets")
  public ResponseEntity<List<VetDto>> getAllVetRecords() {
    List<VetDto> vetDetails = null;
    if (adminReadService != null) {
      vetDetails = adminReadService.getAllVetRecords();
    }
    return ResponseEntity.ok().body(vetDetails);
  }

  /**
   * Gets all the user records
   *
   * @return
   */
  @GetMapping("/all-users")
  public ResponseEntity<List<UserDto>> getAllUserRecords() {
    List<UserDto> userDetails = null;
    if (adminReadService != null) {
      userDetails = adminReadService.getAllUserRecords();
    }
    return ResponseEntity.ok().body(userDetails);
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

  @PostMapping("/post-animal")
  public ResponseEntity<ApiResponse> addAnimal(@RequestBody Object requestBody) {
    logger.info("Recieved request as :", requestBody.toString());
    AnimalDto animalDto = null;
    if (CommonUtils.isStrictTypeOf(requestBody, Animal.class)) {
      animalDto =
        ObjectMapperWrapper
          .getInstance()
          .convertValue(requestBody, AnimalDto.class);
      apiResponse.setBody(adminReadService.addAnimal(animalDto));
      apiResponse.setMessage("successfully inserted object");
      apiResponse.setSuccess(true);
      apiResponse.setError(false);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
  }
}
