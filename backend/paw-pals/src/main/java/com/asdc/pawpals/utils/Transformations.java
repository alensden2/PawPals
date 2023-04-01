package com.asdc.pawpals.utils;

import com.asdc.pawpals.dto.*;
import com.asdc.pawpals.model.*;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Transformations {

  public class MODEL_TO_DTO_CONVERTER {

    public static AnimalDto animal(Animal dao) {
      AnimalDto dto = new AnimalDto();
      if (dao != null) {
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setType(dao.getType());
        dto.setAge(dao.getAge());
        dto.setGender(dao.getGender());
        dto.setPhotoUrl(dao.getPhotoUrl());
        dto.setOwnerId(dao.getOwner().getUser().getUserId());
        if (
          dao.getMedicalHistories() != null &&
          !dao.getMedicalHistories().isEmpty()
        ) {
          dto.setMedicalHistory(
            dao
              .getMedicalHistories()
              .stream()
              .map(MODEL_TO_DTO_CONVERTER::medicalHistory)
              .collect(Collectors.toList())
          );
        }
      }
      return dto;
    }

    public static PetOwnerDto petOwner(PetOwner dao) {
      PetOwnerDto dto = new PetOwnerDto();
      if (dao != null) {
        dto.setFirstName(dao.getFirstName());
        dto.setLastName(dao.getLastName());
        dto.setPhoneNo(dao.getPhoneNo());
        dto.setAddress(dao.getAddress());
        dto.setPhotoUrl(dao.getPhotoUrl());
        if (dao.getAnimals() != null && !dao.getAnimals().isEmpty()) {
          dto.setPets(
            dao
              .getAnimals()
              .stream()
              .map(MODEL_TO_DTO_CONVERTER::animal)
              .collect(Collectors.toList())
          );
        }
        dto.setUserName(dao.getUser().getUserId());
      }
      return dto;
    }

    public static MedicalHistoryDto medicalHistory(MedicalHistory dao) {
      MedicalHistoryDto dto = new MedicalHistoryDto();
      if (dao != null) {
        dto.setDateDiagnosed(dao.getDateDiagnosed());
        dto.setAilmentName(dao.getAilmentName());
        dto.setPrescription(dao.getPrescription());
        dto.setVaccines(dao.getVaccines());
        if(dao.getVet() != null 
          && dao.getVet().getUser() != null 
          && dao.getVet().getUser().getUserId() != null){
            dto.setVetUserId(dao.getVet().getUser().getUserId());
        }
        if(dao.getAnimal() != null){
          dto.setAnimalId(dao.getAnimal().getId());
        }
      }
      return dto;
    }

    public static VetDto vet(Vet dao) {
      VetDto dto = new VetDto();
      if (dao != null) {
        dto.setFirstName(dao.getFirstName());
        dto.setLastName(dao.getLastName());
        dto.setLicenseNumber(dao.getLicenseNumber());
        dto.setExperience(dao.getExperience());
        dto.setQualification(dao.getQualification());
        dto.setClinicAddress(dao.getClinicAddress());
        dto.setClinicUrl(dao.getClinicUrl());
        dto.setProfileStatus(dto.getProfileStatus());
        if(dao.getUser() != null && dao.getUser().getUserId() != null){
          dto.setUserName(dao.getUser().getUserId());
        }
      }
      return dto;
    }

    public static VetAvailabilityDto vetAvailability(VetAvailability dao) {
      VetAvailabilityDto dto = new VetAvailabilityDto();
      if (dao != null) {
        dto.setAvailabilityId(dao.getId());
        dto.setDayOfWeek(dao.getDayOfWeek());
        Pair<String, String> slot = Pair.of(
          dao.getStartTime(),
          dao.getEndTime()
        );
        dto.setSlots(Arrays.asList(slot));
        if (dao.getVet() != null && dao.getVet().getUser() != null) {
          dto.setVetUserId(dao.getVet().getUser().getUserId());
        }
      }
      return dto;
    }

    public static AppointmentDto appointment(Appointment dao) {
      AppointmentDto dto = new AppointmentDto();
      if (dao != null) {
        dto.setId(dao.getId());
        dto.setDate(dao.getDate());
        dto.setStartTime(dao.getStartTime());
        dto.setEndTime(dao.getEndTime());
        dto.setStatus(dao.getStatus());
        if (dao.getVet() != null && dao.getVet().getUser() != null) {
          dto.setVetUserId(dao.getVet().getUser().getUserId());
        }
        if (dao.getAnimal() != null) {
          dto.setAnimalId(dao.getAnimal().getId());
        }
      }
      return dto;
    }

    public static UserDto user(User user) {
      UserDto userDto = new UserDto();
      if (user != null) {
        userDto.setEmail(user.getEmail());
        userDto.setUserName(user.getUserId());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
      }
      return userDto;
    }
  }

  public class DTO_TO_MODEL_CONVERTER {

    public static Vet vet(VetDto vetDto) {
      Vet vet = new Vet();
      if (vetDto != null) {
        vet.setFirstName(vetDto.getFirstName());
        vet.setLastName(vetDto.getLastName());
        vet.setLicenseNumber(vetDto.getLicenseNumber());
        vet.setExperience(vetDto.getExperience());
        vet.setQualification(vetDto.getQualification());
        vet.setClinicAddress(vetDto.getClinicAddress());
        vet.setClinicUrl(vetDto.getClinicUrl());
        vet.setProfileStatus(vetDto.getProfileStatus());
        if (vetDto.getUsername() != null) {
          User user = new User();
          user.setUserId(vetDto.getUsername());
          vet.setUser(user);
        }
      }
      return vet;
    }

    public static PetOwner petOwner(PetOwnerDto petOwnerDto) {
      PetOwner petOwner = new PetOwner();
      if (petOwnerDto != null) {
        //

        petOwner.setPhotoUrl(petOwnerDto.getPhotoUrl());

        petOwner.setFirstName(petOwnerDto.getFirstName());
        petOwner.setAddress(petOwnerDto.getAddress());
        petOwner.setPhoneNo(petOwnerDto.getPhoneNo());
        petOwner.setLastName(petOwnerDto.getLastName());

        if (petOwnerDto.getPets() != null && !petOwnerDto.getPets().isEmpty()) {
          petOwner.setAnimals(
            petOwnerDto
              .getPets()
              .stream()
              .map(DTO_TO_MODEL_CONVERTER::animal)
              .collect(Collectors.toList())
          );
        }
      }
      return petOwner;
    }

    public static VetAvailability vetAvailability(VetAvailabilityDto dto) {
      VetAvailability dao = new VetAvailability();
      if (dto != null) {
        dao.setId(dto.getAvailabilityId());
        dao.setDayOfWeek(dto.getDayOfWeek());
        if (dto.getSlots() != null && !dto.getSlots().isEmpty()) {
          dao.setStartTime(dto.getSlots().get(0).getFirst());
          dao.setEndTime(dto.getSlots().get(0).getSecond());
        }

        Vet vet = new Vet();
        User user = new User();
        user.setUserId(dto.getVetUserId());
        vet.setUser(user);
        dao.setVet(vet);
      }
      return dao;
    }

    public static Appointment appointment(AppointmentDto dto) {
      Appointment dao = new Appointment();
      if (dto != null) {
        dao.setId(dto.getId());
        dao.setDate(dto.getDate());
        dao.setStartTime(dto.getStartTime());
        dao.setEndTime(dto.getEndTime());
        dao.setStatus(dto.getStatus().toUpperCase());
        Vet vet = new Vet();
        User user = new User();
        user.setUserId(dto.getVetUserId());
        vet.setUser(user);
        dao.setVet(vet);

        Animal animal = new Animal();
        animal.setId(dto.getAnimalId());
        dao.setAnimal(animal);
      }
      return dao;
    }

    public static Animal animal(AnimalDto animalDto) {
      Animal animal = new Animal();
      if (animalDto != null) {
        animal.setId(animalDto.getId());
        animal.setAge(animalDto.getAge());
        animal.setName(animalDto.getName());
        animal.setType(animalDto.getType());
        animal.setAge(animalDto.getAge());
        animal.setGender(animalDto.getGender());
        animal.setPhotoUrl(animalDto.getPhotoUrl());

        if (
          animalDto.getMedicalHistory() != null &&
          !animalDto.getMedicalHistory().isEmpty()
        ) {
          animal.setMedicalHistories(
            animalDto
              .getMedicalHistory()
              .stream()
              .map(DTO_TO_MODEL_CONVERTER::medicalHistory)
              .collect(Collectors.toList())
          );
        }
      }

      return animal;
    }

    public static MedicalHistory medicalHistory(MedicalHistoryDto dto) {
      MedicalHistory dao = new MedicalHistory();
      if (dto != null) {
        dao.setDateDiagnosed(dto.getDateDiagnosed());
        dao.setAilmentName(dto.getAilmentName());
        dao.setPrescription(dto.getPrescription());
        dao.setVaccines(dto.getVaccines());
        Vet vet = new Vet();
        User user = new User();
        user.setUserId(dto.getVetUserId());
        vet.setUser(user);
        dao.setVet(vet);
        Animal animal = new Animal();
        animal.setId(dto.getAnimalId());
        dao.setAnimal(animal);
      }
      return dao;
    }

    public static User user(UserDto userDto) {
      User user = new User();

      if (userDto != null) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUserId(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
      }
      return user;
    }
  }
}
