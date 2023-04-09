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
import com.asdc.pawpals.utils.Constants;
import com.asdc.pawpals.utils.Transformations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.InvalidObjectException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentServiceImplTest {

    @Mock
    AnimalRepository animalRepositoryMock;

    @Mock
    AppointmentRepository appointmentRepositoryMock;

    @Mock
    VetRepository vetRepositoryMock;

    @InjectMocks
    AppointmentServiceImpl appointmentServiceImpl;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateAppointmentStatus() throws InvalidAppointmentId, InvalidObjectException {
        // Arrange
        Integer appointmentId = 1;
        String action = "confirmed";
        Appointment appointment = new Appointment();
        appointment.setId(appointmentId);
        appointment.setStatus("pending");

        // Act
        when(appointmentRepositoryMock.findById(anyInt())).thenReturn(Optional.of(appointment));
        when(appointmentRepositoryMock.save(any(Appointment.class))).thenReturn(appointment);
        Boolean result = appointmentServiceImpl.updateAppointmentStatus(appointmentId, action);

        // Assert
        assertTrue(result);
        assertEquals(action, appointment.getStatus());
    }

    @Test
    public void testUpdateAppointmentStatusForInvalidInput() {
        // Arrange
        Integer appointmentId = null;
        String action = null;

        // Act and Assert
        assertThrows(InvalidObjectException.class, () -> appointmentServiceImpl.updateAppointmentStatus(appointmentId, action));
    }

    @Test
    public void testUpdateAppointmentStatusForInvalidAppointmentId() {
        // Arrange
        Integer appointmentId = 1;
        String action = "confirmed";

        // Act
        when(appointmentRepositoryMock.findById(anyInt())).thenReturn(Optional.empty());

        // Assert
        assertThrows(InvalidAppointmentId.class, () -> appointmentServiceImpl.updateAppointmentStatus(appointmentId, action));
    }

    @Test
    public void testUpdateAppointmentStatusForNullInput() {
// Arrange
        Integer appointmentId = null;
        String action = null;


// Act and Assert
        InvalidObjectException exception = assertThrows(InvalidObjectException.class, () -> appointmentServiceImpl.updateAppointmentStatus(appointmentId, action));
        assertEquals("Invalid input provided", exception.getMessage());
    }


    @Test
    public void testBookAppointmentForValidInput() throws InvalidVetID, InvalidObjectException, UserNameNotFound, InvalidAnimalId {
        // Arrange
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(1);
        appointmentDto.setDate("01-01-2023");
        appointmentDto.setStartTime("10:00");
        appointmentDto.setEndTime("11:00");
        appointmentDto.setStatus(Constants.STATUS[2]);
        appointmentDto.setAnimalId(1L);
        appointmentDto.setVetUserId("123456");

        Vet vet = new Vet();
        Animal animal = new Animal();

        when(vetRepositoryMock.findByUser_UserId(any(String.class))).thenReturn(Optional.of(vet));
        when(animalRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(animal));
        when(appointmentRepositoryMock.save(any(Appointment.class))).thenReturn(Transformations.DTO_TO_MODEL_CONVERTER.appointment(appointmentDto));

        //Act
        appointmentDto = appointmentServiceImpl.bookAppointment(appointmentDto);

        //Assert
        assertNotNull(appointmentDto);
        assertNotNull(appointmentDto.getId());
        assertNotNull(appointmentDto.getStartTime());
        assertNotNull(appointmentDto.getEndTime());
        assertNotNull(appointmentDto.getAnimalId());
        assertNotNull(appointmentDto.getVetUserId());
        assertEquals(Constants.STATUS[2], appointmentDto.getStatus());

    }

    @Test
    public void testBookAppointmentForInvalidDate() {
        // Arrange
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setDate("2023-04-06");
        appointmentDto.setStartTime("10:00:00");
        appointmentDto.setEndTime("11:00:00");
        appointmentDto.setStatus(Constants.STATUS[2]);
        appointmentDto.setAnimalId(1L);
        appointmentDto.setVetUserId("123456");

        //Act
        InvalidObjectException exception = assertThrows(InvalidObjectException.class, () -> appointmentServiceImpl.bookAppointment(appointmentDto));

        //Assert
        assertEquals("Invalid Appointment Object", exception.getMessage());
    }

    @Test
    public void testBookAppointmentForInvalidAnimalId() throws UserNameNotFound {
        // Arrange
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(1);
        appointmentDto.setDate("01-01-2023");
        appointmentDto.setStartTime("10:00");
        appointmentDto.setEndTime("11:00");
        appointmentDto.setStatus(Constants.STATUS[2]);
        appointmentDto.setAnimalId(1L);
        appointmentDto.setVetUserId("123456");

        when(vetRepositoryMock.findByUser_UserId(any(String.class))).thenReturn(Optional.of(new Vet()));
        when(animalRepositoryMock.findById(any(Long.class))).thenReturn(Optional.empty());

        //Act + Assert
        InvalidAnimalId exception = assertThrows(InvalidAnimalId.class, () -> appointmentServiceImpl.bookAppointment(appointmentDto));

        assertNotNull(exception);
    }

    @Test
    public void testBookAppointmentForInvalidVetId() throws UserNameNotFound {
        // Arrange
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(1);
        appointmentDto.setDate("01-01-2023");
        appointmentDto.setStartTime("10:00");
        appointmentDto.setEndTime("11:00");
        appointmentDto.setStatus(Constants.STATUS[2]);
        appointmentDto.setAnimalId(1L);
        appointmentDto.setVetUserId("123456");

        when(vetRepositoryMock.findByUser_UserId(any(String.class))).thenReturn(Optional.empty());

        //Act
        UserNameNotFound exception = assertThrows(UserNameNotFound.class, () -> appointmentServiceImpl.bookAppointment(appointmentDto));

        //Assert
        assertNotNull(exception);
    }
}