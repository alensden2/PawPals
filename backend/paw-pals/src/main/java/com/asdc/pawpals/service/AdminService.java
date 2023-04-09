package com.asdc.pawpals.service;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.InvalidVetID;
import com.asdc.pawpals.exception.PetOwnerAlreadyDoesNotExists;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.Vet;

import java.util.List;

public interface AdminService {
    public List<AnimalDto> getAllAnimalRecords();

    public List<VetDto> getAllVetRecords();

    public List<PetOwnerDto> getAllPetOwnerRecords();

    public List<UserDto> getAllUserRecords();

    public AnimalDto addAnimal(Animal animal) throws PetOwnerAlreadyDoesNotExists;

    public VetDto addVet(Vet vet) throws UserNameNotFound;

    public VetDto deleteVet(Long id) throws InvalidVetID;

    public AnimalDto deleteAnimal(Long id) throws InvalidAnimalId;

    public VetDto updateVet(Long id, Vet updatedVet) throws InvalidVetID;

    public AnimalDto updateAnimal(Long id, Animal updatedAnimal) throws InvalidAnimalId;
}
