package com.asdc.pawpals.controller;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asdc.pawpals.dto.VetAvailabilityDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.dto.VetScheduleDto;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.service.VetService;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.ObjectMapperWrapper;
import com.fasterxml.jackson.core.type.TypeReference;

@RestController()
@RequestMapping("/unauth/vet")
public class VetController {

    private static final Logger logger = LogManager.getLogger(VetController.class);

    @Autowired
    VetService vetService;

    @GetMapping("/{id}")
    public String getVetById(@PathVariable Integer id){
        logger.info("VetController :: getVetById :: Entering with Id {}", id);
        return "Hello "+id;
    }

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> registerVet(@RequestPart("vet") Object requestBody, @RequestPart("clinicPhoto") MultipartFile clinicPhoto) throws IOException, InvalidImage{
        Boolean vetRegistered = false;
        ResponseEntity<String> response = null;
        try{
            VetDto vet = null;
            if(CommonUtils.isStrictTypeOf(requestBody, VetDto.class)){
                vet = ObjectMapperWrapper.getInstance().convertValue(requestBody, VetDto.class);
                vet.setClinicUrl(CommonUtils.getBytes(clinicPhoto));
                vetRegistered = vetService.registerVet(vet);
                response = vetRegistered ? ResponseEntity.ok(vet.getUsername()) : ResponseEntity.internalServerError().body("There was some error, please try again");
            }
            else{
                response = ResponseEntity.badRequest().body("Invalid input provided");
            }
        } catch(UsernameNotFoundException e){
            response = ResponseEntity.badRequest().body("User name provided is invalid");
        }
        return response;
    }

    /**
     * /availability
     * request body:{
     *  "date":"<value>"
     * }
     */
    @PostMapping("/availability/{id}")
    public ResponseEntity<VetAvailabilityDto> getAvailability(@PathVariable(value = "id") String userId, @RequestBody Object requestBody){
        VetAvailabilityDto availability = null;
        ResponseEntity<VetAvailabilityDto> response = null;
        if(CommonUtils.isStrictTypeOf(requestBody, new TypeReference<Map<String, String>>(){}) && 
            userId != null && !userId.isEmpty()){
                Map<String, String> request = ObjectMapperWrapper.getInstance().convertValue(requestBody,  new TypeReference<Map<String, String>>(){});
                String date = request.get("date");
                availability = vetService.getVetAvailabilityOnSpecificDay(userId, date);
                response = ResponseEntity.ok(availability);
        }
        else{
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @PostMapping("/schedule/{id}")
    public ResponseEntity<VetScheduleDto> getVetSchedule(@PathVariable(value = "id") String userId, @RequestBody Object requestBody){
        VetScheduleDto vetScheduleDto = null;
        ResponseEntity<VetScheduleDto> response = null;
        if(CommonUtils.isStrictTypeOf(requestBody, new TypeReference<Map<String, String>>(){}) && 
            userId != null && !userId.isEmpty()){
                Map<String, String> request = ObjectMapperWrapper.getInstance().convertValue(requestBody,  new TypeReference<Map<String, String>>(){});
                String date = request.get("date");
                vetScheduleDto = vetService.getVetScheduleOnSpecificDay(userId, date);
                response = ResponseEntity.ok(vetScheduleDto);
        }
        else{
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

}
