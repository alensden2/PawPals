package com.asdc.pawpals.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.service.AdminReadService;
import com.asdc.pawpals.service.implementation.AdminReadServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/admin")
public class AdminReadController {
    // private static final Logger logger = LogManager.getLogger(AdminReadController.class);

    @Autowired
    AdminReadService adminReadService;

    @GetMapping("/all")
    public List<Animal> getAllAnimalRecords(){
        if(adminReadService.getAllAnimalRecords() == null){
            List<Animal> emptyEntries = null;
            return emptyEntries;
        }
        return adminReadService.getAllAnimalRecords();
    }
}
