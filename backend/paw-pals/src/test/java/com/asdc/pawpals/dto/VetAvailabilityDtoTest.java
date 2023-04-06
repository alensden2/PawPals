package com.asdc.pawpals.dto;

import org.junit.Test;
import org.springframework.data.util.Pair;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VetAvailabilityDtoTest {

    @Test
    public void testGettersAndSetters() {
        VetAvailabilityDto dto = new VetAvailabilityDto();
        dto.setAvailabilityId(1);
        dto.setDayOfWeek("Monday");
        dto.setSlots(Arrays.asList(Pair.of("9:00", "12:00"), Pair.of("14:00", "18:00")));
        dto.setVetUserId("V001");

        assertEquals(Integer.valueOf(1), dto.getAvailabilityId());
        assertEquals("Monday", dto.getDayOfWeek());
        assertEquals(Arrays.asList(Pair.of("9:00", "12:00"), Pair.of("14:00", "18:00")), dto.getSlots());
        assertEquals("V001", dto.getVetUserId());
    }

    @Test
    public void testNoArgsConstructor() {
        VetAvailabilityDto dto = new VetAvailabilityDto();

        assertNull(dto.getAvailabilityId());
        assertNull(dto.getDayOfWeek());
        assertNull(dto.getSlots());
        assertNull(dto.getVetUserId());
    }


}
