package com.asdc.pawpals.service;

import java.util.List;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.exception.PetOwnerAlreadyDoesNotExists;
import com.asdc.pawpals.model.Animal;

public interface AdminReadService {
  public List<AnimalDto> getAllAnimalRecords();

  public List<VetDto> getAllVetRecords();

  public List<UserDto> getAllUserRecords();

  public AnimalDto addAnimal(Animal animal) throws PetOwnerAlreadyDoesNotExists;
}
