package com.asdc.pawpals.utils;

import com.asdc.pawpals.dto.*;
import com.asdc.pawpals.model.*;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * The Transformations class provides a MODEL_TO_DTO_CONVERTER nested class that is responsible for converting
 * <p>
 * a model object to a DTO (Data Transfer Object) object.
 */
public class Transformations {

    public class MODEL_TO_DTO_CONVERTER {

        /**
         * This method converts an Animal DAO object to an AnimalDto object.
         *
         * @param dao - the Animal DAO object to be converted.
         * @return an AnimalDto object representing the converted Animal DAO object.
         */
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

        /**
         * This method converts a PetOwner DAO object to a PetOwnerDto object.
         *
         * @param dao - the PetOwner DAO object to be converted.
         * @return a PetOwnerDto object representing the converted PetOwner DAO object.
         */
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

        /**
         * This method converts a MedicalHistory DAO object to a MedicalHistoryDto object.
         *
         * @param dao - the MedicalHistory DAO object to be converted.
         * @return a MedicalHistoryDto object representing the converted MedicalHistory DAO object.
         */
        public static MedicalHistoryDto medicalHistory(MedicalHistory dao) {
            MedicalHistoryDto dto = new MedicalHistoryDto();
            if (dao != null) {
                dto.setDateDiagnosed(dao.getDateDiagnosed());
                dto.setAilmentName(dao.getAilmentName());
                dto.setPrescription(dao.getPrescription());
                dto.setVaccines(dao.getVaccines());
                if (dao.getVet() != null
                        && dao.getVet().getUser() != null
                        && dao.getVet().getUser().getUserId() != null) {
                    dto.setVetUserId(dao.getVet().getUser().getUserId());
                }
                if (dao.getAnimal() != null) {
                    dto.setAnimalId(dao.getAnimal().getId());
                }
            }
            return dto;
        }

        /**
         * This method converts a MedicalHistory DAO object to a MedicalHistoryDtoInline object.
         *
         * @param dao - the MedicalHistory DAO object to be converted.
         * @return a MedicalHistoryDtoInline object representing the converted MedicalHistory DAO object.
         */
        public static MedicalHistoryDtoInline medicalHistoryInline(MedicalHistory dao) {
            MedicalHistoryDtoInline dto = new MedicalHistoryDtoInline();
            if (dao != null) {
                dto.setDateDiagnosed(dao.getDateDiagnosed());
                dto.setAilmentName(dao.getAilmentName());
                dto.setPrescription(dao.getPrescription());
                dto.setVaccines(dao.getVaccines());
                if (dao.getVet() != null) {
                    dto.setVet(MODEL_TO_DTO_CONVERTER.vet(dao.getVet()));
                }
                if (dao.getAnimal() != null) {
                    dto.setAnimalId(dao.getAnimal().getId());
                }
            }
            return dto;
        }

        /**
         * This method converts a Vet DAO object to a VetDto object.
         * It sets the fields of the VetDto object based on the values of the fields in the Vet DAO object.
         * If the Vet DAO object is null, the method returns null.
         *
         * @param dao the Vet DAO object to convert
         * @return a VetDto object representing the same data as the input Vet DAO object, or null if the input object is null
         */
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
                dto.setProfileStatus(dao.getProfileStatus());
                dto.setPhoneNo(dao.getPhoneNo());
                if (dao.getUser() != null) {
                    dto.setEmail(dao.getUser().getEmail());
                }
                if (dao.getUser() != null && dao.getUser().getUserId() != null) {
                    dto.setUserName(dao.getUser().getUserId());
                }
            }
            return dto;
        }

        /**
         * Converts a VetAvailability entity to its corresponding data transfer object (DTO) representation.
         *
         * @param dao The VetAvailability entity to convert.
         * @return A VetAvailabilityDto object containing the converted data.
         */
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

        /**
         * Converts an {@link Appointment} object to an {@link AppointmentDto} object.
         *
         * @param dao the {@link Appointment} object to be converted
         * @return the converted {@link AppointmentDto} object
         */
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

        /**
         * Converts a User entity to a UserDto object.
         *
         * @param user the User entity to be converted.
         * @return a UserDto object representing the converted entity.
         */
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
        /**
         * Converts a VetDto object to a Vet object.
         *
         * @param vetDto the VetDto object to be converted
         * @return a Vet object
         */
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
                vet.setPhoneNo(vetDto.getPhoneNo());
                if (vetDto.getUsername() != null) {
                    User user = new User();
                    user.setUserId(vetDto.getUsername());
                    vet.setUser(user);
                }
            }
            return vet;
        }

        /**
         * Converts a PetOwnerDto object to a PetOwner object.
         *
         * @param petOwnerDto the PetOwnerDto object to convert
         * @return the resulting PetOwner object
         */
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

        /**
         * Converts a {@link VetAvailabilityDto} object to a {@link VetAvailability} object.
         *
         * @param dto the {@link VetAvailabilityDto} object to convert
         * @return a {@link VetAvailability} object converted from the input DTO
         */
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

        /**
         * Converts an {@link AppointmentDto} object to an {@link Appointment} object.
         *
         * @param dto the {@link AppointmentDto} object to be converted
         * @return the corresponding {@link Appointment} object
         */
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

        /**
         * Converts an AnimalDto to an Animal model object.
         *
         * @param animalDto the AnimalDto to be converted
         * @return the converted Animal object
         */
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

        /**
         * Converts a MedicalHistoryDto object to a MedicalHistory object.
         *
         * @param dto the MedicalHistoryDto object to be converted
         * @return the converted MedicalHistory object
         */
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

        /**
         * Converts a UserDto object to a User object.
         *
         * @param userDto The UserDto object to convert.
         * @return The converted User object.
         */
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
