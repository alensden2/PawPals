package com.asdc.pawpals.controller;

import com.asdc.pawpals.service.VetService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/vet")
public class VetController {

    private static final Logger logger = LogManager.getLogger(VetController.class);

    @Autowired
    VetService vetService;

    @GetMapping("/{id}")
    public String getVetById(@PathVariable Integer id){
        logger.info("VetController :: getVetById :: Entering with Id {}", id);
        return "Hello "+id;
    }


}
