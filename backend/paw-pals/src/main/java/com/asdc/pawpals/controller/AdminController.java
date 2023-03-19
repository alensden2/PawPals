package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.exception.PetOwnerAlreadyDoesNotExists;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.service.AdminService;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.ObjectMapperWrapper;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  AdminService adminReadService;

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

  @PostMapping("/post-animal")
  public ResponseEntity<ApiResponse> addAnimal(@RequestBody Object requestBody)
    throws PetOwnerAlreadyDoesNotExists {
    logger.info("Recieved request as :", requestBody.toString());
    Animal animal = null;
    if (CommonUtils.isStrictTypeOf(requestBody, Animal.class)) {
      animal =
        ObjectMapperWrapper
          .getInstance()
          .convertValue(requestBody, Animal.class);
      apiResponse.setBody(adminReadService.addAnimal(animal));
      apiResponse.setMessage("successfully inserted object");
      apiResponse.setSuccess(true);
      apiResponse.setError(false);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
  }

  @DeleteMapping("/delete-animal/{id}")
  public ResponseEntity<ApiResponse> deleteAnimal(@PathVariable Long id) {
    logger.info(
      "Received delete request for Animal with id: {}",
      id.toString()
    );
    if (CommonUtils.isStrictTypeOf(id, Long.class)) {
      id = ObjectMapperWrapper.getInstance().convertValue(id, Long.class);
      apiResponse.setBody(adminReadService.deleteAnimal(id));
      apiResponse.setMessage("successfully deleted object");
      apiResponse.setSuccess(true);
      apiResponse.setError(false);
    }
    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
  }

  @PostMapping("/post-vet")
  public ResponseEntity<ApiResponse> addVet(@RequestBody Object requestBody) {
    logger.info("Recieved message as :", requestBody.toString());
    Vet vet = null;
    if (CommonUtils.isStrictTypeOf(requestBody, Vet.class)) {
      vet =
        ObjectMapperWrapper.getInstance().convertValue(requestBody, Vet.class);
      apiResponse.setBody(adminReadService.addVet(vet));
      apiResponse.setMessage("successfully inserted object");
      apiResponse.setSuccess(true);
      apiResponse.setError(false);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
  }

  @PutMapping("/update-animal/{id}")
  public ResponseEntity<ApiResponse> updateAnimal(
    @PathVariable("id") Long id,
    @RequestBody Object requestBody
  )
    throws PetOwnerAlreadyDoesNotExists {
    logger.info("Received request as: {}", requestBody.toString());
    Animal animal = null;
    if (CommonUtils.isStrictTypeOf(requestBody, Animal.class)) {
      animal =
        ObjectMapperWrapper
          .getInstance()
          .convertValue(requestBody, Animal.class);
      animal.setId(id); // Set the ID of the updated animal
      apiResponse.setBody(adminReadService.updateAnimal(id, animal));
      apiResponse.setMessage("Successfully updated object");
      apiResponse.setSuccess(true);
      apiResponse.setError(false);
    }
    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
  }

  @PutMapping("/update-vet/{id}")
  public ResponseEntity<ApiResponse> updateVet(
    @PathVariable Long id,
    @RequestBody Object requestBody
  ) {
    logger.info("Received message as:", requestBody.toString());
    Vet vet = null;
    if (CommonUtils.isStrictTypeOf(requestBody, Vet.class)) {
      vet =
        ObjectMapperWrapper.getInstance().convertValue(requestBody, Vet.class);
      vet.setId(id);
      apiResponse.setBody(adminReadService.updateVet(id, vet));
      apiResponse.setMessage("successfully updated object");
      apiResponse.setSuccess(true);
      apiResponse.setError(false);
    }
    return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
  }

  @DeleteMapping("/delete-vet/{id}")
  public ResponseEntity<ApiResponse> deleteVet(@PathVariable Long id) {
    logger.info("Received delete request for Vet with id: {}", id.toString());
    if (CommonUtils.isStrictTypeOf(id, Long.class)) {
      id = ObjectMapperWrapper.getInstance().convertValue(id, Long.class);
      apiResponse.setBody(adminReadService.deleteVet(id));
      apiResponse.setMessage("successfully deleted object");
      apiResponse.setSuccess(true);
      apiResponse.setError(false);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
  }
}
