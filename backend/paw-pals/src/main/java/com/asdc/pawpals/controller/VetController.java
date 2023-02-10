package com.asdc.pawpals.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/vet")
public class VetController {
	
    // private static final Logger logger = LogManager.getLogger(VetController.class);
    
    @GetMapping("/{id}")
    public ResponseEntity<String> getVet(@PathVariable String id){
        return ResponseEntity.ok("");
    }
}
