package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.Enums.Status;
import com.asdc.pawpals.dto.*;
import com.asdc.pawpals.exception.InvalidAppointmentId;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.exception.NoAppointmentExist;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.Appointment;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.model.VetAvailability;
import com.asdc.pawpals.repository.AppointmentRepository;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.repository.VetAvailabilityRepository;
import com.asdc.pawpals.repository.VetRepository;
import com.asdc.pawpals.service.VetService;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.Transformations;
import com.asdc.pawpals.validators.AppointmentValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Lazy
public class VetServiceImpl implements VetService {

    @Autowired
    VetRepository vetRepository;

    @Autowired
    VetAvailabilityRepository vetAvailabilityRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailServiceImpl mailService;

    @Override
    public Boolean registerVet(VetDto vetDto) throws UsernameNotFoundException {
        Boolean vetRegistered = false;
        if (vetDto != null) {
            Vet vet = Transformations.DTO_TO_MODEL_CONVERTER.vet(vetDto);
            //check if user with given username exists, then only register that user as vet
            Optional<User> user = userRepository.findById(vetDto.getUsername());
            if (!user.isEmpty()) {
                Long oldCount = vetRepository.count();
                vet.setId(oldCount + 1);
                vet.setProfileStatus(Status.PENDING.getLabel());
                vetRepository.save(vet);
                mailService.sendMail(vet.getUser().getEmail(), "pending for Approval", "your application is under process for admin approval");
                Long newCount = vetRepository.count();
                vetRegistered = ((oldCount + 1) == newCount);
            } else {
                throw new UsernameNotFoundException("Please provide a valid username");
            }
        }
        return vetRegistered;
    }

    @Override
    public List<VetAvailabilityDto> getVetAvailability(String userId) {
        List<VetAvailability> availability = vetAvailabilityRepository.findByVet_User_UserId(userId);
        List<VetAvailabilityDto> availabilityDto = null;
        if (availability != null) {
            availabilityDto = availability.stream().map(Transformations.MODEL_TO_DTO_CONVERTER::vetAvailability).collect(Collectors.toList());
        }
        return availabilityDto;
    }

    @Override
    public List<VetAvailabilityDto> getVetAvailability(Long vetId) {
        List<VetAvailability> availability = vetAvailabilityRepository.findByVetId(vetId);
        List<VetAvailabilityDto> availabilityDto = null;
        if (availability != null) {
            availabilityDto = availability.stream().map(Transformations.MODEL_TO_DTO_CONVERTER::vetAvailability).collect(Collectors.toList());
        }
        return availabilityDto;
    }

    @Override
    public VetAvailabilityDto getVetAvailabilityOnSpecificDay(String userId, String date) {
        List<VetAvailability> availability = vetAvailabilityRepository.findByVet_User_UserId(userId);
        List<Appointment> appointments = appointmentRepository.findByVet_User_UserId(userId);
        return findVetAvailabilityOnSpecificDay(availability, appointments, date);
    }

    @Override
    public VetAvailabilityDto getVetAvailabilityOnSpecificDay(Long vetId, String date) {
        List<VetAvailability> availability = vetAvailabilityRepository.findByVetId(vetId);
        List<Appointment> appointments = appointmentRepository.findByVetId(vetId);
        return findVetAvailabilityOnSpecificDay(availability, appointments, date);
    }

    @Override
    public VetScheduleDto getVetScheduleOnSpecificDay(String userId, String date) {
        List<Appointment> appointments = appointmentRepository.findByVet_User_UserId(userId);
        VetScheduleDto vetSchedule = new VetScheduleDto();
        vetSchedule.setVetUserId(userId);
        List<Pair<String, String>> slotsBooked = new ArrayList<>();
        if (appointments != null) {
            appointments.stream().filter(Objects::nonNull)
                    .filter(apt -> apt.getDate() != null)
                    .filter(apt -> apt.getDate().equals(date))
                    .filter(apt -> apt.getStatus() != null)
                    .filter(apt -> apt.getStatus().equals(Status.CONFIRMED.getLabel()))
                    .forEach(appointment -> {
                        slotsBooked.add(Pair.of(appointment.getStartTime(), appointment.getEndTime()));
                    });
            vetSchedule.setSlotsBooked(slotsBooked);
        }
        return vetSchedule;
    }

    /**
     * @param appointmentDto
     * @param id
     * @return
     */
    @Override
    public AppointmentDto changeStatus(AppointmentDto appointmentDto, Integer id) throws InvalidAppointmentId {

        Appointment appointment = appointmentRepository.findById(id).orElseThrow(InvalidAppointmentId::new);
        Appointment returnedAppointment = null;
        if (appointmentDto.getStatus() != null) {
            appointment.setStatus(appointmentDto.getStatus());
        }
        if (appointmentDto.getDate() != null) {
            appointment.setDate(appointmentDto.getDate());
        }
        if (appointmentDto.getStartTime() != null) {
            appointment.setStartTime(appointmentDto.getStartTime());
        }
        if (appointmentDto.getEndTime() != null) {
            appointment.setEndTime(appointmentDto.getEndTime());
        }
        if (AppointmentValidators.isValidAppointment(appointment.getDate(), appointment.getStartTime(), appointment.getEndTime(), appointment.getStatus())) {
            returnedAppointment = appointmentRepository.saveAndFlush(appointment);
        }
        return Transformations.MODEL_TO_DTO_CONVERTER.appointment(returnedAppointment);

    }

    /**
     * @param vetId
     * @return
     */
    @Override
    public List<VetAppointmentDto> retrieveAllPets(String vetId) throws UserNameNotFound, NoAppointmentExist {
        Vet vet = vetRepository.findByUser_UserId(vetId).orElseThrow(UserNameNotFound::new);

        if (vet.getAppointment().isEmpty()) {
            throw new NoAppointmentExist("No appointment exist for vet" + vetId);
        }

        return vet.getAppointment().stream().map(appointment -> {
            VetAppointmentDto vetAppointmentDto = new VetAppointmentDto();
            vetAppointmentDto.setAppointmentDto(Transformations.MODEL_TO_DTO_CONVERTER.appointment(appointment));
            vetAppointmentDto.setAnimalDto(Transformations.MODEL_TO_DTO_CONVERTER.animal(appointment.getAnimal()));
            vetAppointmentDto.setPetOwnerDto(Transformations.MODEL_TO_DTO_CONVERTER.petOwner(appointment.getAnimal().getOwner()));
            vetAppointmentDto.setMedicalHistoryDtos(appointment.getAnimal().getMedicalHistories().stream().map(m ->
                    Transformations.MODEL_TO_DTO_CONVERTER.medicalHistory(m)).collect(Collectors.toList())
            );
            return vetAppointmentDto;
        }).collect(Collectors.toList());
    }

    /**
     * @return
     */
    @Override
    public List<VetDto> retrieveAllVets() {
        return vetRepository.findAll().stream().
                filter(v -> v.getProfileStatus().equals(Status.PENDING)).
                map(v -> Transformations.MODEL_TO_DTO_CONVERTER.vet(v)).
                collect(Collectors.toList());
    }

    /**
     * @param vetDto
     * @param id
     * @return
     */
    @Override
    public VetDto updateVet(VetDto vetDto, String id, MultipartFile image) throws UserNameNotFound, IOException, InvalidImage {

        if (null != id && !id.isEmpty() && vetDto != null && image != null) {
            Vet vet = vetRepository.findByUser_UserId(id).orElseThrow(UserNameNotFound::new);
            if (vetDto.getClinicAddress() != null) {
                vet.setClinicAddress(vetDto.getClinicAddress());
            }
            if (vetDto.getClinicUrl() != null) {
                vet.setClinicUrl(CommonUtils.getBytes(image));
            }
            if (vetDto.getExperience() != null) {
                vet.setExperience(vetDto.getExperience());
            }
            if (vetDto.getQualification() != null) {
                vet.setQualification(vetDto.getQualification());
            }
            if (vetDto.getProfileStatus() != null) {
                if (vetDto.getProfileStatus().equals(Status.CONFIRMED.getLabel())) {
                    mailService.sendMail(vet.getUser().getEmail(), "Successfully Approved", "your profile is successfully approved by Admin");
                } else if (vetDto.getProfileStatus().equals(Status.REJECTED.getLabel())) {
                    mailService.sendMail(vet.getUser().getEmail(), "Profile Decline", "Unfortunately your profile has been rejected by Admin");
                }
                vet.setQualification(vetDto.getProfileStatus());
            }
            if (vetDto.getLicenseNumber() != null) {
                vet.setLicenseNumber(vetDto.getLicenseNumber());
            }
            if (vetDto.getFirstName() != null) {
                vet.setFirstName(vetDto.getFirstName());
            }
            if (vetDto.getLastName() != null) {
                vet.setLastName(vetDto.getLastName());
            }
            if (vetDto.getClinicAddress() != null) {
                vet.setClinicUrl(vet.getClinicUrl());
            }

            vetRepository.saveAndFlush(vet);
            return Transformations.MODEL_TO_DTO_CONVERTER.vet(vet);

        } else if (vetDto == null) {
            throw new InvalidObjectException("Invalid pet owner object body");
        } else {
            throw new UserNameNotFound("User name is not found for " + id);
        }
    }


    private VetAvailabilityDto findVetAvailabilityOnSpecificDay(List<VetAvailability> availability, List<Appointment> appointments, String date) {
        VetAvailabilityDto availabilityDto = null;
        if (availability != null) {
            //get the original vet availability on the specific day mentioned (derived from date given)
            availabilityDto = availability.stream().map(Transformations.MODEL_TO_DTO_CONVERTER::vetAvailability)
                    .filter(avl -> avl.getDayOfWeek().equalsIgnoreCase(CommonUtils.getDayFromDate(date))).findFirst().orElse(null);

            if (availabilityDto != null) {
                List<Pair<String, String>> freeSlots = new ArrayList<>();
                String starTime = availabilityDto.getSlots().get(0).getFirst();
                String endTime = availabilityDto.getSlots().get(0).getSecond();

                String currentSlot = starTime;
                while (!currentSlot.equals(endTime)) {
                    final String fCurrSlot = currentSlot;
                    Boolean appointmentBooked = appointments.stream().filter(Objects::nonNull)
                            .filter(apt -> apt.getDate() != null)
                            .filter(apt -> apt.getDate().equals(date))
                            .filter(apt -> apt.getStatus() != null)
                            .filter(apt -> apt.getStatus().equals(Status.CONFIRMED.getLabel()))
                            .anyMatch(apt -> {
                                return apt.getStartTime().equals(fCurrSlot);
                            });
                    if (!appointmentBooked) {
                        freeSlots.add(Pair.of(currentSlot, CommonUtils.getNextSlotTime(currentSlot)));
                    }
                    currentSlot = CommonUtils.getNextSlotTime(currentSlot);
                }

                availabilityDto.setSlots(freeSlots);

            }
        }
        return availabilityDto;
    }

}
