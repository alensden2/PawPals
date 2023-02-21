package com.asdc.pawpals.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.Vet;

public interface AdminReadService {
    public List<Animal> getAllAnimalRecords();
    public List<Vet> getAllVetRecords();
}
