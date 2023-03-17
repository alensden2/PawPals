package com.asdc.pawpals.service;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.exception.PetOwnerAlreadyDoesNotExists;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.Vet;
import java.util.List;

public interface AdminService {
  public List<AnimalDto> getAllAnimalRecords();

  public List<VetDto> getAllVetRecords();

  public List<UserDto> getAllUserRecords();

  public AnimalDto addAnimal(Animal animal) throws PetOwnerAlreadyDoesNotExists;

  public VetDto addVet(Vet vet);
}
