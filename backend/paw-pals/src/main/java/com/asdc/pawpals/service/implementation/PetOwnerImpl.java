package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.*;
import com.asdc.pawpals.exception.*;
import com.asdc.pawpals.model.*;
import com.asdc.pawpals.repository.*;
import com.asdc.pawpals.service.PetOwnerService;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.Transformations;
import com.asdc.pawpals.validators.AppointmentValidators;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public List<AnimalDto> retrieveAllPets(String ownerId) throws NoPetRegisterUnderPetOwner, UserNameNotFound {
        logger.debug("Get All Pets By owner Id", ownerId);

        PetOwner petOwner = petOwnerRepository.findByUser_UserId(ownerId).orElseThrow(UserNameNotFound::new);
        List<AnimalDto> animalDtoList = petOwner.getAnimals().stream().map(Transformations.MODEL_TO_DTO_CONVERTER::animal).collect(Collectors.toList());
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
    public AppointmentDto bookAppointment(AppointmentDto appointmentDto) throws InvalidAnimalId, InvalidObjectException, UserNameNotFound {
        logger.debug("Book pet owner appointment with vet", appointmentDto.toString());
        if (appointmentDto != null && appointmentDto.getDate() != null && appointmentDto.getStartTime() != null &&
                appointmentDto.getEndTime() != null && appointmentDto.getStatus() != null
                && AppointmentValidators.isValidAppointment(appointmentDto.getDate(), appointmentDto.getStartTime(), appointmentDto.getEndTime(), appointmentDto.getStatus())) {
            Appointment appointment = Transformations.DTO_TO_MODEL_CONVERTER.appointment(appointmentDto);
            Vet vet = vetRepository.findByUser_UserId(appointmentDto.getVetUserId()).orElseThrow(UserNameNotFound::new);
            Animal animal = animalRepository.findById(appointmentDto.getAnimalId()).orElseThrow(InvalidAnimalId::new);
            appointment.setVet(vet);
            appointment.setAnimal(animal);
            appointment = appointmentRepository.save(appointment);
            return Transformations.MODEL_TO_DTO_CONVERTER.appointment(appointment);
        } else {
            throw new InvalidObjectException("Invalid Appointment Object");
        }


    }

    /**
     * @param id
     * @param petOwnerDto
     * @param image
     * @return
     */
    @Override
    public PetOwnerDto updatePetOwner(String id, PetOwnerDto petOwnerDto, MultipartFile image) throws UserNameNotFound, InvalidPetOwnerObject, InvalidImage, IOException {

        if (null != id && !id.isEmpty() && petOwnerDto != null) {
            PetOwner petOwner = petOwnerRepository.findByUser_UserId(id).orElseThrow(UserNameNotFound::new);

            if (petOwnerDto.getAddress() != null) {
                petOwner.setAddress(petOwnerDto.getAddress());
            }
            if (petOwnerDto.getPhotoUrl() != null) {
                petOwner.setPhotoUrl(CommonUtils.getBytes(image));
            }
            if (petOwnerDto.getPhoneNo() != null) {
                petOwner.setPhoneNo(petOwner.getPhoneNo());
            }
            if (petOwnerDto.getFirstName() != null) {
                petOwner.setFirstName(petOwnerDto.getFirstName());
            }
            if (petOwnerDto.getLastName() != null) {
                petOwner.setLastName(petOwnerDto.getLastName());
            }

            petOwnerRepository.saveAndFlush(petOwner);

            return Transformations.MODEL_TO_DTO_CONVERTER.petOwner(petOwner);


        } else if (petOwnerDto == null) {
            throw new InvalidPetOwnerObject("Invalid pet owner object body");
        } else {
            throw new UserNameNotFound("User name is not found for " + id);
        }

    }

    /**
     * @param id
     * @return
     */
    @Override
    public PetOwnerDto deletePetOwner(String id) throws UserNameNotFound {
        if (null != id && !id.isEmpty()) {
            PetOwner petOwner = petOwnerRepository.findByUser_UserId(id).orElseThrow(UserNameNotFound::new);
            petOwnerRepository.delete(petOwner.getId());
            petOwnerRepository.flush();
//            PetOwner petOwner1 = petOwnerRepository.findByUser_UserId(id).orElseThrow(UserNameNotFound::new);
            return Transformations.MODEL_TO_DTO_CONVERTER.petOwner(petOwner);
        } else {
            throw new UserNameNotFound("invalid id");
        }
    }

    /**
     * @param ownerId
     * @return
     */
    @Override
    public List<PetAppointmentsDto> retrievePetsAppointments(String ownerId) throws UserNameNotFound, NoPetRegisterUnderPetOwner {
        logger.debug("Get All Pets Appointment By owner Id", ownerId);

        PetOwner petOwner = petOwnerRepository.findByUser_UserId(ownerId).orElseThrow(UserNameNotFound::new);

        if (petOwner.getAnimals().isEmpty()) {
            throw new NoPetRegisterUnderPetOwner("No Pet Registered for Owner " + petOwner.getFirstName() + " " + petOwner.getLastName());
        }


        List<PetAppointmentsDto> petAppointmentsDtos = petOwner.getAnimals().stream().flatMap(animal -> animal.getAppointment().stream()
                .map(appointment -> {
                    PetAppointmentsDto petAppointmentsDto = new PetAppointmentsDto();
                    petAppointmentsDto.setAppointmentDto(Transformations.MODEL_TO_DTO_CONVERTER.appointment(appointment));
                    petAppointmentsDto.setVetDto(Transformations.MODEL_TO_DTO_CONVERTER.vet(appointment.getVet()));
                    petAppointmentsDto.setAnimalDto(Transformations.MODEL_TO_DTO_CONVERTER.animal(animal));
                    return petAppointmentsDto;
                })).collect(Collectors.toList());

        return petAppointmentsDtos;

    }

    /**
     * @param ownerId
     * @return
     */
    @Override
    public List<PetMedicalHistoryDto> retrievePetsMedicalHistory(String ownerId) throws UserNameNotFound, NoPetRegisterUnderPetOwner {

        logger.debug("Get All Pets Medical Records By owner Id", ownerId);

        PetOwner petOwner = petOwnerRepository.findByUser_UserId(ownerId).orElseThrow(UserNameNotFound::new);

        if (petOwner.getAnimals().isEmpty()) {
            throw new NoPetRegisterUnderPetOwner("No Pet Registered for Owner " + petOwner.getFirstName() + " " + petOwner.getLastName());
        }


        List<PetMedicalHistoryDto> petMedicalHistoryDtos = petOwner.getAnimals().stream().flatMap(animal -> animal.getMedicalHistories().stream()
                .map(medicalHistory -> {
                    PetMedicalHistoryDto petMedicalHistoryDto = new PetMedicalHistoryDto();
                    petMedicalHistoryDto.setMedicalHistoryDto(Transformations.MODEL_TO_DTO_CONVERTER.medicalHistory(medicalHistory));
                    petMedicalHistoryDto.setVetDto(Transformations.MODEL_TO_DTO_CONVERTER.vet(medicalHistory.getVet()));
                    petMedicalHistoryDto.setAnimalDto(Transformations.MODEL_TO_DTO_CONVERTER.animal(animal));
                    return petMedicalHistoryDto;
                })).collect(Collectors.toList());

        return petMedicalHistoryDtos;

    }


}
