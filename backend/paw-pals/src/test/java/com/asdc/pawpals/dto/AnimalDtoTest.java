package com.asdc.pawpals.dto;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnimalDtoTest {

    @Test
    public void testAnimalDto() {
        AnimalDto animalDto = new AnimalDto(1L, "Buddy", "Dog", 2, "Male", "ownerId",
                new Byte[]{1, 0, 1}, Arrays.asList(new MedicalHistoryDto()));
        Assertions.assertEquals(1L, animalDto.getId());
        Assertions.assertEquals("Buddy", animalDto.getName());
        Assertions.assertEquals("Dog", animalDto.getType());
        Assertions.assertEquals(2, animalDto.getAge());
        Assertions.assertEquals("Male", animalDto.getGender());
        Assertions.assertEquals("ownerId", animalDto.getOwnerId());
        Assertions.assertTrue(Arrays.equals(new Byte[]{1, 0, 1}, animalDto.getPhotoUrl()));
        Assertions.assertEquals(1, animalDto.getMedicalHistory().size());
    }

}
