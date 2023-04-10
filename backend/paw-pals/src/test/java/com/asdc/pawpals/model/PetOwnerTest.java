package com.asdc.pawpals.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PetOwnerTest {

    @Test
    public void testGettersAndSetters() {
        Long ownerId = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String phoneNo = "1234567890";
        Byte[] photoUrl = {0x01, 0x02, 0x03};
        String address = "123 Main St";
        List<Animal> animals = new ArrayList<>();
        User user = new User();

        PetOwner petOwner = new PetOwner();
        petOwner.setId(ownerId);
        petOwner.setFirstName(firstName);
        petOwner.setLastName(lastName);
        petOwner.setPhoneNo(phoneNo);
        petOwner.setPhotoUrl(photoUrl);
        petOwner.setAddress(address);
        petOwner.setAnimals(animals);
        petOwner.setUser(user);

        Assertions.assertEquals(ownerId, petOwner.getId());
        Assertions.assertEquals(firstName, petOwner.getFirstName());
        Assertions.assertEquals(lastName, petOwner.getLastName());
        Assertions.assertEquals(phoneNo, petOwner.getPhoneNo());
        Assertions.assertArrayEquals(photoUrl, petOwner.getPhotoUrl());
        Assertions.assertEquals(address, petOwner.getAddress());
        Assertions.assertEquals(animals, petOwner.getAnimals());
        Assertions.assertEquals(user, petOwner.getUser());
    }
}
