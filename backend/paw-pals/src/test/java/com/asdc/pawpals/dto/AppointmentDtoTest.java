package com.asdc.pawpals.dto;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppointmentDtoTest {

    @Test
    public void testGettersAndSetters() {
        AppointmentDto dto = new AppointmentDto();
        assertNull(dto.getId());
        assertNull(dto.getDate());
        assertNull(dto.getStartTime());
        assertNull(dto.getEndTime());
        assertNull(dto.getVetUserId());
        assertNull(dto.getAnimalId());
        assertNull(dto.getStatus());

        dto.setId(1);
        assertEquals(Integer.valueOf(1), dto.getId());

        dto.setDate("08-04-2023");
        assertEquals("08-04-2023", dto.getDate());

        dto.setStartTime("09:00");
        assertEquals("09:00", dto.getStartTime());

        dto.setEndTime("10:00");
        assertEquals("10:00", dto.getEndTime());

        dto.setVetUserId("VET123");
        assertEquals("VET123", dto.getVetUserId());

        dto.setAnimalId(123L);
        assertEquals(Long.valueOf(123L), dto.getAnimalId());

        dto.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", dto.getStatus());
    }

}
