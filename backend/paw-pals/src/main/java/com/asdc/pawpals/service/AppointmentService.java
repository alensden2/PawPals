package com.asdc.pawpals.service;

import java.io.InvalidObjectException;

import com.asdc.pawpals.dto.AppointmentDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.InvalidAppointmentId;
import com.asdc.pawpals.exception.InvalidVetID;
import com.asdc.pawpals.exception.UserNameNotFound;

public interface AppointmentService {
    public Boolean updateAppointmentStatus(Integer appointmentId, String action) throws InvalidObjectException, InvalidAppointmentId;

    public AppointmentDto bookAppointment(AppointmentDto appointmentDto) throws InvalidVetID, InvalidAnimalId, InvalidObjectException, UserNameNotFound;
}
