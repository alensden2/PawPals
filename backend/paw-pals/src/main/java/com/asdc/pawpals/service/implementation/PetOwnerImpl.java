package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.AppointmentDto;
import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.exception.*;
import com.asdc.pawpals.model.*;
import com.asdc.pawpals.repository.*;
import com.asdc.pawpals.service.PetOwnerService;
import com.asdc.pawpals.utils.Transformations;
import com.asdc.pawpals.validators.AppointmentValidators;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Lazy
public class PetOwnerImpl implements PetOwnerService {

    Logger logger = LogManager.getLogger(PetOwnerImpl.class);
    @Autowired
    PetOwnerRepository petOwnerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    VetRepository vetRepository;

    /**
     * @param petOwnerDto
     * @return
     */
    @Override
    public PetOwnerDto registerPetOwner(PetOwnerDto petOwnerDto) throws UserNameNotFound, InvalidUserDetails, UserAlreadyExist {
        logger.debug("Register pet Owner", petOwnerDto);
        if (null != petOwnerDto && null != petOwnerDto.getUsername() &&
                null != petOwnerDto.getPhotoUrl() && null != petOwnerDto.getPhoneNo() &&
                null != petOwnerDto.getAddress()) {
            User user = userRepository.findById(petOwnerDto.getUsername()).orElseThrow(UserNameNotFound::new);

            if (petOwnerRepository.existsByUser_UserId(petOwnerDto.getUsername())) {
                throw new UserAlreadyExist("Pet Owner Already Exist");
            }

            PetOwner petOwner = Transformations.DTO_TO_MODEL_CONVERTER.petOwner(petOwnerDto);
            petOwner.setUser(user);
            petOwner = petOwnerRepository.save(petOwner);
            return Transformations.MODEL_TO_DTO_CONVERTER.petOwner(petOwner);
        } else {
            throw new InvalidUserDetails("incorrect pet owner data");
        }

    }

    /**
     * @param ownerId
     * @return
     */
    @Override
    public List<AnimalDto> retrieveAllPets(Long ownerId) throws InvalidOwnerID, NoPetRegisterUnderPetOwner {
        logger.debug("Get All Pets By owner Id", ownerId);
        PetOwner petOwner = petOwnerRepository.findById(ownerId).orElseThrow(InvalidOwnerID::new);
        List<AnimalDto> animalDtoList = animalRepository.findAll().stream().
                filter(a -> a.getOwner().getId() == ownerId).
                map(Transformations.MODEL_TO_DTO_CONVERTER::animal).collect(Collectors.toList());
        if (animalDtoList.isEmpty()) {
            throw new NoPetRegisterUnderPetOwner("No Pet Registered for Owner " + petOwner.getFirstName() + " " + petOwner.getLastName());
        }
        return animalDtoList;
    }

    /**
     * @param appointmentDto
     * @return
     */
    @Override
    public AppointmentDto bookAppointment(AppointmentDto appointmentDto) throws InvalidVetID, InvalidAnimalId, InvalidObjectException {
        logger.debug("Book pet owner appointment with vet", appointmentDto.toString());
//        AppointmentDto returnAppointmentDto = null;
        if (appointmentDto != null && appointmentDto.getDate() != null && appointmentDto.getStartTime() != null &&
                appointmentDto.getEndTime() != null && appointmentDto.getStatus() != null
                && AppointmentValidators.isValidAppointment(appointmentDto.getDate(), appointmentDto.getStartTime(), appointmentDto.getEndTime(), appointmentDto.getStatus())) {
            Appointment appointment = Transformations.DTO_TO_MODEL_CONVERTER.appointment(appointmentDto);
            Vet vet = vetRepository.findById(appointmentDto.getVetId()).orElseThrow(InvalidVetID::new);
            Animal animal = animalRepository.findById(appointmentDto.getAnimalId()).orElseThrow(InvalidAnimalId::new);
            appointment.setVet(vet);
            appointment.setAnimal(animal);
            appointment = appointmentRepository.save(appointment);
            return Transformations.MODEL_TO_DTO_CONVERTER.appointment(appointment);
        } else {
            throw new InvalidObjectException("Invalid Appointment Object");
        }


    }
}
