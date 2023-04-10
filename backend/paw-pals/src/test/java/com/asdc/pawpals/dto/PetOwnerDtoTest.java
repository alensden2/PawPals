package com.asdc.pawpals.dto;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PetOwnerDtoTest {

    @Test
    public void testPetOwnerDtoConstructorAndGetters() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String phoneNo = "555-1234";
        Byte[] photoUrl = new Byte[] {1, 2, 3};
        String address = "123 Main St";
        List<AnimalDto> pets = new ArrayList<>();
        AnimalDto pet1 = new AnimalDto();
        AnimalDto pet2 = new AnimalDto();
        pets.add(pet1);
        pets.add(pet2);

        // Act
        PetOwnerDto petOwner = new PetOwnerDto();
        petOwner.setFirstName(firstName);
        petOwner.setLastName(lastName);
        petOwner.setAddress(address);
        petOwner.setEmail(email);
        petOwner.setPhotoUrl(photoUrl);
        petOwner.setPhoneNo(phoneNo);

        // Assert
        assertEquals(firstName, petOwner.getFirstName());
        assertEquals(lastName, petOwner.getLastName());
        assertEquals(email, petOwner.getEmail());
        assertEquals(phoneNo, petOwner.getPhoneNo());
        assertArrayEquals(photoUrl, petOwner.getPhotoUrl());

    }

    @Test
    public void testPetOwnerDtoSetters() {
        // Arrange
        PetOwnerDto petOwner = new PetOwnerDto();
        String firstName = "Jane";
        String lastName = "Smith";
        String email = "jane.smith@example.com";
        String phoneNo = "555-5678";
        Byte[] photoUrl = new Byte[] {4, 5, 6};
        String address = "456 Oak St";
        List<AnimalDto> pets = new ArrayList<>();
        AnimalDto pet1 = new AnimalDto();
        AnimalDto pet2 = new AnimalDto();
        pets.add(pet1);
        pets.add(pet2);

        // Act
        petOwner.setFirstName(firstName);
        petOwner.setLastName(lastName);
        petOwner.setEmail(email);
        petOwner.setPhoneNo(phoneNo);
        petOwner.setPhotoUrl(photoUrl);
        petOwner.setAddress(address);
        petOwner.setPets(pets);

        // Assert
        assertEquals(firstName, petOwner.getFirstName());
        assertEquals(lastName, petOwner.getLastName());
        assertEquals(email, petOwner.getEmail());
        assertEquals(phoneNo, petOwner.getPhoneNo());
        assertArrayEquals(photoUrl, petOwner.getPhotoUrl());
        assertEquals(address, petOwner.getAddress());
        assertEquals(pets, petOwner.getPets());
    }

}
