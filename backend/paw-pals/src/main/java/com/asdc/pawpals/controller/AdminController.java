package com.asdc.pawpals.controller;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.exception.PetOwnerAlreadyDoesNotExists;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.service.AdminService;
import com.asdc.pawpals.utils.ApiResponse;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.ObjectMapperWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth/admin")
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
     * Retrieves all animal records from the system.
     *
     * @return ResponseEntity object containing a List of AnimalDto objects representing all the animal records in the system.
     * The response status is set to OK (200) if the request is successful.
     * The response body contains the List of AnimalDto objects.
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
     * Retrieves all the details of all the vets present in the system.
     *
     * @return ResponseEntity with the list of all vet details and a success status code.
     * If no vets are present, an empty list will be returned.
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
     * Retrieves all the pet owner records from the database.
     *
     * @return A ResponseEntity containing a list of PetOwnerDto objects, wrapped in a ApiResponse object with a 200 status code if successful, or an empty list and a 404 status code if no pet owner records exist.
     */
    @GetMapping("/all-pet-owners")
    public ResponseEntity<List<PetOwnerDto>> getAllPetOwnerRecords() {
        List<PetOwnerDto> petOwnerRecords = null;
        if (adminReadService != null) {
            petOwnerRecords = adminReadService.getAllPetOwnerRecords();
        }
        return ResponseEntity.ok().body(petOwnerRecords);
    }

    /**
     * Retrieves all user records from the system and returns them as a List of UserDto objects in the response body.
     *
     * @return ResponseEntity containing a List of UserDto objects in the response body and HttpStatus.OK as the status code.
     */
    @GetMapping("/all-users")
    public ResponseEntity<List<UserDto>> getAllUserRecords() {
        List<UserDto> userDetails = null;
        if (adminReadService != null) {
            userDetails = adminReadService.getAllUserRecords();
        }
        return ResponseEntity.ok().body(userDetails);
    }

    /**
     * This method handles the HTTP POST request to add a new animal to the system.
     *
     * @param requestBody the request body containing the animal object to be added
     * @return a ResponseEntity object containing an ApiResponse object with the result of the operation
     * @throws PetOwnerAlreadyDoesNotExists if the owner of the animal does not exist in the system
     */
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

    /**
     * Deletes the Animal object from the database with the given id.
     *
     * @param id the id of the Animal object to be deleted
     * @return a ResponseEntity containing an ApiResponse object with details about the operation performed
     */
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


    /**
     * Handles the HTTP POST request to add a new Vet to the system.
     *
     * @param requestBody the request body, in JSON format, containing the information of the new Vet to be added
     * @return a ResponseEntity with an ApiResponse containing the added Vet information on the body, and a success message
     */
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

    /**
     * Updates an animal with the specified ID.
     *
     * @param id          The ID of the animal to update.
     * @param requestBody The request body containing the updated animal information.
     * @return A ResponseEntity with the ApiResponse containing the updated animal information.
     * @throws PetOwnerAlreadyDoesNotExists If the specified animal ID does not exist.
     */
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

    /**
     * Updates an existing Vet object with the given id with the information provided in the request body.
     *
     * @param id          The id of the Vet object to be updated.
     * @param requestBody The request body containing the updated information for the Vet object.
     * @return A ResponseEntity object containing an ApiResponse object with the updated Vet object, along with a success message and a status code of 200 (OK).
     */
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

    /**
     * Deletes a Vet object with the specified ID from the system.
     *
     * @param id The ID of the Vet object to be deleted.
     * @return A ResponseEntity with an ApiResponse indicating the result of the operation.
     */
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
