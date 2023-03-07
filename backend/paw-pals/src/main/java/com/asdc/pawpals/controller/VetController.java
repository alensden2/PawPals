package com.asdc.pawpals.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.service.VetService;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.ObjectMapperWrapper;

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

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerVet(@RequestBody Object requestBody){
        logger.info("Recieved request as :", requestBody.toString());
        Boolean vetRegistered = false;
        
        if(CommonUtils.isStrictTypeOf(requestBody, VetDto.class)){
            VetDto vet = ObjectMapperWrapper.getInstance().convertValue(requestBody, VetDto.class);
            vetRegistered = vetService.registerVet(vet);
        }
        return ResponseEntity.ok(vetRegistered);
    }


}
