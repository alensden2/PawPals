package com.asdc.pawpals.service;

import com.asdc.pawpals.dto.*;
import com.asdc.pawpals.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.List;

@Service
public interface PetOwnerService {
    PetOwnerDto registerPetOwner(PetOwnerDto petOwner) throws UserNameNotFound, InvalidUserDetails, UserAlreadyExist;

    List<AnimalDto> retrieveAllPets(String ownerId) throws NoPetRegisterUnderPetOwner, UserNameNotFound;

    AppointmentDto bookAppointment(AppointmentDto appointmentDto) throws InvalidVetID, InvalidAnimalId, InvalidObjectException, UserNameNotFound;

    PetOwnerDto updatePetOwner(String id, PetOwnerDto petOwnerDto, MultipartFile image) throws UserNameNotFound, InvalidPetOwnerObject, InvalidImage, IOException;

    PetOwnerDto deletePetOwner(String id) throws UserNameNotFound;


    List<PetAppointmentsDto> retrievePetsAppointments(String ownerId) throws UserNameNotFound, NoPetRegisterUnderPetOwner;

    List<PetMedicalHistoryDto> retrievePetsMedicalHistory(String ownerId) throws UserNameNotFound, NoPetRegisterUnderPetOwner;
}
