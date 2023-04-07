package com.asdc.pawpals.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentTest {


    @Test
    void testSetters() {
        Animal animal1 = new Animal();
        Vet vet1 = new Vet();
        Animal animal2 = new Animal();
        Vet vet2 = new Vet();
        Appointment appointment = new Appointment();
        appointment.setDate("2023-04-06");
        appointment.setStartTime("09:00");
        appointment.setEndTime("10:00");
        appointment.setStatus("CONFIRMED");
        appointment.setAnimal(animal1);
        appointment.setVet(vet1);
        assertEquals("2023-04-06", appointment.getDate());
        assertEquals("09:00", appointment.getStartTime());
        assertEquals("10:00", appointment.getEndTime());
        assertEquals("CONFIRMED", appointment.getStatus());
        assertEquals(animal1, appointment.getAnimal());
        assertEquals(vet1, appointment.getVet());
        appointment.setAnimal(animal2);
        appointment.setVet(vet2);
        assertEquals(animal2, appointment.getAnimal());
        assertEquals(vet2, appointment.getVet());
    }

}
