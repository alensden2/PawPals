package com.asdc.pawpals.service;

import org.springframework.stereotype.Service;

import com.asdc.pawpals.dto.VetDto;

@Service
public interface VetService {

    public Boolean registerVet(VetDto vet);
}
