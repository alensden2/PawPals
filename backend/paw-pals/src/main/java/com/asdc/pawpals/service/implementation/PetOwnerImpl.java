package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.repository.PetOwnerRepository;
import com.asdc.pawpals.service.PetOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PetOwnerImpl implements PetOwnerService {

    @Autowired
    PetOwnerRepository petOwnerRepository;


}