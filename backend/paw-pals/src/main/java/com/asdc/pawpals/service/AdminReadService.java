package com.asdc.pawpals.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.model.Animal;

public interface AdminReadService {
    public List<Animal> getAllAnimalRecords();
}
