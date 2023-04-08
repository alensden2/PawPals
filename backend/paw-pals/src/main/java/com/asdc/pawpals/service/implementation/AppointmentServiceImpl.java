package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.AppointmentDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.InvalidAppointmentId;
import com.asdc.pawpals.exception.InvalidVetID;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.Appointment;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.repository.AnimalRepository;
import com.asdc.pawpals.repository.AppointmentRepository;
import com.asdc.pawpals.repository.VetRepository;
import com.asdc.pawpals.service.AppointmentService;
import com.asdc.pawpals.utils.Constants;
import com.asdc.pawpals.utils.Transformations;
import com.asdc.pawpals.validators.AppointmentValidators;
import java.io.InvalidObjectException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class AppointmentServiceImpl implements AppointmentService {
  Logger logger = LogManager.getLogger(AppointmentServiceImpl.class);

  @Autowired
  AnimalRepository animalRepository;

  @Autowired
  AppointmentRepository appointmentRepository;

  @Autowired
  VetRepository vetRepository;

  /**

    *    Updates the status of a given appointment.
    *    @param appointmentId the ID of the appointment to update
    *    @param action the new status of the appointment
    *    @return a boolean indicating if the status was updated successfully
    *    @throws InvalidObjectException if either parameter is null or empty
    *    @throws InvalidAppointmentId if the appointment ID is invalid
*/
  public Boolean updateAppointmentStatus(Integer appointmentId, String action)
    throws InvalidObjectException, InvalidAppointmentId {
    logger.debug(
      "Update Appointment status for pet owner from vet side %s",
      appointmentId + action
    );
    Boolean statusUpdated = false;
    if (
      appointmentId != null &&
      action != null &&
      !action.isEmpty() &&
      AppointmentValidators.isValidStatus(action)
    ) {
      Optional<Appointment> apt = appointmentRepository.findById(appointmentId);
      if (apt.isPresent()) {
        Appointment appointment = apt.get();
        appointment.setStatus(action);
        statusUpdated = appointmentRepository.save(appointment) != null;
      } else {
        throw new InvalidAppointmentId("Appointment id is wrong");
      }
    } else {
      throw new InvalidObjectException("Invalid input provided");
    }
    return statusUpdated;
  }

  /**

* Books a new appointment with the specified appointment details
* @param appointmentDto appointment details to be booked
* @return appointment details of the booked appointment
* @throws InvalidVetID if the vet id provided in the appointment details is invalid
* @throws InvalidAnimalId if the animal id provided in the appointment details is invalid
* @throws InvalidObjectException if the appointment details provided is invalid
* @throws UserNameNotFound if the username of the vet is not found
*/
  @Override
  public AppointmentDto bookAppointment(AppointmentDto appointmentDto)
    throws InvalidVetID, InvalidAnimalId, InvalidObjectException, UserNameNotFound {
    logger.debug("Book pet owner appointment with vet %s", appointmentDto);
    if (appointmentDto == null) {
      throw new InvalidObjectException("Invalid Appointment Object");
    }
    appointmentDto.setStatus(Constants.STATUS[2]); //set status to pending manually
    if (
      appointmentDto.getDate() != null &&
      appointmentDto.getStartTime() != null &&
      appointmentDto.getEndTime() != null &&
      AppointmentValidators.isValidAppointment(
        appointmentDto.getDate(),
        appointmentDto.getStartTime(),
        appointmentDto.getEndTime(),
        appointmentDto.getStatus()
      )
    ) {
      Appointment appointment = Transformations.DTO_TO_MODEL_CONVERTER.appointment(
        appointmentDto
      );
      Vet vet = vetRepository
        .findByUser_UserId(appointmentDto.getVetUserId())
        .orElseThrow(UserNameNotFound::new);
      Animal animal = animalRepository
        .findById(appointmentDto.getAnimalId())
        .orElseThrow(InvalidAnimalId::new);
      appointment.setVet(vet);
      appointment.setAnimal(animal);
      appointment = appointmentRepository.save(appointment);
      return Transformations.MODEL_TO_DTO_CONVERTER.appointment(appointment);
    } else {
      throw new InvalidObjectException("Invalid Appointment Object");
    }
  }
}
