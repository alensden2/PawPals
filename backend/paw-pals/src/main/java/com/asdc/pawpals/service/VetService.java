package com.asdc.pawpals.service;

import java.util.List;

import com.asdc.pawpals.dto.*;
import com.asdc.pawpals.exception.InvalidAppointmentId;
import com.asdc.pawpals.exception.NoAppointmentExist;
import com.asdc.pawpals.exception.UserNameNotFound;
import org.springframework.stereotype.Service;

@Service
public interface VetService {

    public Boolean registerVet(VetDto vet);
    public List<VetAvailabilityDto> getVetAvailability(String userId);
    public List<VetAvailabilityDto> getVetAvailability(Long vetId);
    public VetAvailabilityDto getVetAvailabilityOnSpecificDay(String userId, String date);
    public VetAvailabilityDto getVetAvailabilityOnSpecificDay(Long vetId, String date);
    public VetScheduleDto getVetScheduleOnSpecificDay(String userId, String date);

    AppointmentDto changeStatus(AppointmentDto appointmentDto, Integer id) throws InvalidAppointmentId;

    List<VetAppointmentDto>  retrieveAllPets(String vetId) throws UserNameNotFound, NoAppointmentExist;
}
