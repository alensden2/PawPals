package com.asdc.pawpals.dto;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;


@SpringBootTest
public class VetScheduleDtoTest {

    @Test
    public void testConstructorAndGetters() {
        VetScheduleDto vetSchedule = new VetScheduleDto();
        assertEquals(null, vetSchedule.getVetUserId());
        assertEquals(null, vetSchedule.getSlotsBooked());

        List<Pair<String, String>> slotsBooked = Arrays.asList(
                Pair.of("9", "1"),
                Pair.of("1.30", "4.30")
        );
        vetSchedule = new VetScheduleDto();
        vetSchedule.setVetUserId("vet123");
        vetSchedule.setSlotsBooked(slotsBooked);

        assertEquals("vet123", vetSchedule.getVetUserId());
        assertEquals(slotsBooked, vetSchedule.getSlotsBooked());
    }

    @Test
    public void testSetters() {
        VetScheduleDto vetSchedule = new VetScheduleDto();

        List<Pair<String, String>> slotsBooked = Arrays.asList(
                Pair.of("9", "1"),
                Pair.of("1.30", "4.30")
        );
        vetSchedule.setVetUserId("vet123");
        vetSchedule.setSlotsBooked(slotsBooked);

        assertEquals("vet123", vetSchedule.getVetUserId());
        assertEquals(slotsBooked, vetSchedule.getSlotsBooked());

        List<Pair<String, String>> slotsBooked2 = Arrays.asList(
                Pair.of("2", "4"),
                Pair.of("5", "6")
        );
        vetSchedule.setSlotsBooked(slotsBooked2);

        assertEquals("vet123", vetSchedule.getVetUserId());
        assertEquals(slotsBooked2, vetSchedule.getSlotsBooked());
    }

}
