package com.asdc.pawpals.controller;

import com.asdc.pawpals.service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VetController {

    @Autowired
    VetService vetService;


}