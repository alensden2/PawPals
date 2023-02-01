package com.asdc.pawpals.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asdc.pawpals.model.TestModel;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String returnHello(){
        TestModel t = new TestModel();
        t.setTest1(1);
        return "Hello World "+t.getTest1();
    }
}
