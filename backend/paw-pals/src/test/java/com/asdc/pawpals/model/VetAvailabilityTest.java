package com.asdc.pawpals.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class VetAvailabilityTest {

    @Test
    public void testGettersAndSetters() {
        VetAvailability availability = new VetAvailability();
        availability.setId(1);
        availability.setDayOfWeek("Monday");
        availability.setStartTime("9:00 AM");
        availability.setEndTime("5:00 PM");

        Vet vet = new Vet();
        vet.setId(2L);
        availability.setVet(vet);

        assertEquals(Integer.valueOf(1), availability.getId());
        assertEquals("Monday", availability.getDayOfWeek());
        assertEquals("9:00 AM", availability.getStartTime());
        assertEquals("5:00 PM", availability.getEndTime());
        assertNotNull(availability.getVet());
        assertEquals(Long.valueOf(2), availability.getVet().getId());
    }

}
