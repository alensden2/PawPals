package com.asdc.pawpals.utils;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.MedicalHistoryDto;
import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.MedicalHistory;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.MedicalHistory;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.model.Vet;
import java.util.stream.Collectors;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.data.util.Pair;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.AppointmentDto;
import com.asdc.pawpals.dto.MedicalHistoryDto;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.dto.VetAvailabilityDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.Appointment;
import com.asdc.pawpals.model.MedicalHistory;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.model.VetAvailability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Transformations {

    public class MODEL_TO_DTO_CONVERTER {
        public static AnimalDto animal(Animal dao) {
            AnimalDto dto = new AnimalDto();
            if (dao != null) {
                dto.setName(dao.getName());
                dto.setType(dao.getType());
                dto.setAge(dao.getAge());
                dto.setGender(dao.getGender());
                if (dao.getMedicalHistories() != null
                        && !dao.getMedicalHistories().isEmpty()) {
                    dto.setMedicalHistory(
                            dao.getMedicalHistories().stream().map(
                                    MODEL_TO_DTO_CONVERTER::medicalHistory
                            ).collect(Collectors.toList())
                    );
                }
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
                dto.setVet(MODEL_TO_DTO_CONVERTER.vet(dao.getVet()));
            }
            return dto;
        }

        public static VetDto vet(Vet dao) {
            VetDto dto = new VetDto();
            if (dao != null) {
                dto.setName(dao.getName());
                dto.setLicenseNumber(dao.getLicenseNumber());
                dto.setExperience(dao.getExperience());
                dto.setQualification(dao.getQualification());
                dto.setClinicAddress(dao.getClinicAddress());
            }
            return dto;
        }

        public static VetAvailabilityDto vetAvailability(VetAvailability dao){
            VetAvailabilityDto dto = new VetAvailabilityDto();
            if(dao != null){
                dto.setAvailabilityId(dao.getId());
                dto.setDayOfWeek(dao.getDayOfWeek());
                Pair<String, String> slot = Pair.of(dao.getStartTime(), dao.getEndTime());
                dto.setSlots(Arrays.asList(slot));
                if(dao.getVet() != null && dao.getVet().getUser() != null){
                    dto.setVetUserId(dao.getVet().getUser().getUserId());
                }
            }
            return dto;
        }

        public static AppointmentDto appointment(Appointment dao){
            AppointmentDto dto = new AppointmentDto();
            if(dao != null){
                dto.setId(dao.getId());
                dto.setDate(dao.getDate());
                dto.setStartTime(dao.getStartTime());
                dto.setEndTime(dao.getEndTime());
                if(dao.getVet() != null && dao.getVet().getUser() != null){
                    dto.setVetUserId(dao.getVet().getUser().getUserId());
                }
                if(dao.getAnimal() != null){
                    dto.setAnimalId(dao.getAnimal().getId());
                }
            }
            return dto;
        }

        public static UserDto user(User user){
            UserDto userDto = new UserDto();
            if(user != null){
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
                vet.setName(vetDto.getName());
                vet.setLicenseNumber(vetDto.getLicenseNumber());
                vet.setExperience(vetDto.getExperience());
                vet.setQualification(vetDto.getQualification());
                vet.setClinicAddress(vetDto.getClinicAddress());
            }
            return vet;
        }

        public static VetAvailability vetAvailability(VetAvailabilityDto dto){
            VetAvailability dao = new VetAvailability();
            if(dto != null){
                dao.setId(dto.getAvailabilityId());
                dao.setDayOfWeek(dto.getDayOfWeek());
                if(dto.getSlots() != null && !dto.getSlots().isEmpty()){
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

        public static Appointment appointment(AppointmentDto dto){
            Appointment dao = new Appointment();
            if(dto != null){
                dao.setId(dto.getId());
                dao.setDate(dto.getDate());
                dao.setStartTime(dto.getStartTime());
                dao.setEndTime(dto.getEndTime());

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
//                animal.setAge(animalDto.getAge());
//                animal.setName(animalDto.getName());
//                animal.setType(animalDto.getName());
//                animal.setAge(animalDto.getAge());
//                animal.setGender(animalDto.getGender());
//
//                 animal.setOwner(animalDto.getOwner());
//
//                PetOwnerDto petOwnerDtoToBeTransformed = animalDto.getOwner();
//                PetOwner petOwnerTransformed = new PetOwner();
//                petOwnerTransformed.setAnimals(petOwnerDtoToBeTransformed.g);
//            }
            }
            return animal;
        }

        public static User user(UserDto userDto){
            User user = new User();
            
    
            if(userDto != null){
                PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
                user.setUserId(userDto.getUsername());
                user.setEmail(userDto.getEmail());
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                user.setRole(userDto.getRole());
            }
            return user;
        }
    }
  }
