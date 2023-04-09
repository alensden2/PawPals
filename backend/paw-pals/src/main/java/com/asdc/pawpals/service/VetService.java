package com.asdc.pawpals.service;

import com.asdc.pawpals.dto.*;
import com.asdc.pawpals.exception.InvalidAppointmentId;
import com.asdc.pawpals.exception.InvalidImage;
import com.asdc.pawpals.exception.NoAppointmentExist;
import com.asdc.pawpals.exception.UserNameNotFound;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.List;

@Service
public interface VetService {

    public Boolean registerVet(VetDto vet);

    public List<VetAvailabilityDto> getVetAvailability(String userId);

    public List<VetAvailabilityDto> getVetAvailability(Long vetId);

    public VetAvailabilityDto getVetAvailabilityOnSpecificDay(String userId, String date);

    public VetAvailabilityDto getVetAvailabilityOnSpecificDay(Long vetId, String date);

    public VetScheduleDto getVetScheduleOnSpecificDay(String userId, String date);

    public List<VetAvailabilityDto> postVetAvailability(List<VetAvailabilityDto> availability) throws InvalidObjectException, UsernameNotFoundException, UserNameNotFound;

    AppointmentDto changeStatus(AppointmentDto appointmentDto, Integer id) throws InvalidAppointmentId;

    List<VetAppointmentDto> retrieveAllPets(String vetId) throws UserNameNotFound, NoAppointmentExist;

    List<VetDto> retrieveAllVets();

    VetDto updateVet(VetDto vetDto, String id, MultipartFile image) throws UserNameNotFound, IOException, InvalidImage;

    VetDto getVetByUserId(String id) throws UserNameNotFound;

    VetDto updateProfileStatus(VetDto vetDto, String id) throws UserNameNotFound, InvalidObjectException;
}
