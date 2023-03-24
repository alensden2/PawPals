package com.asdc.pawpals.service;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.AppointmentDto;
import com.asdc.pawpals.dto.PetAppointmentsDto;
import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.exception.*;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Map;

@Service
public interface PetOwnerService {
    PetOwnerDto registerPetOwner(PetOwnerDto petOwner) throws UserNameNotFound, InvalidUserDetails, UserAlreadyExist;

    List<AnimalDto> retrieveAllPets(Long ownerId) throws InvalidOwnerID, NoPetRegisterUnderPetOwner;

    AppointmentDto bookAppointment(AppointmentDto appointmentDto) throws InvalidVetID, InvalidAnimalId, InvalidObjectException;

    PetOwnerDto updatePetOwner(String id, PetOwnerDto petOwnerDto) throws UserNameNotFound, InvalidPetOwnerObject;

    PetOwnerDto deletePetOwner(String id) throws UserNameNotFound;


    List<PetAppointmentsDto> retrievePetsAppointments(String ownerId) throws UserNameNotFound, NoPetRegisterUnderPetOwner;
}
