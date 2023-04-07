package com.asdc.pawpals.model;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void testGetSetUserId() {
        user.setUserId("testuser");
        Assert.assertEquals("testuser", user.getUserId());
    }

    @Test
    public void testGetSetRole() {
        user.setRole("Vet");
        Assert.assertEquals("Vet", user.getRole());
    }

    @Test
    public void testGetSetEmail() {
        user.setEmail("testuser@example.com");
        Assert.assertEquals("testuser@example.com", user.getEmail());
    }

    @Test
    public void testGetSetPassword() {
        user.setPassword("mypassword");
        Assert.assertEquals("mypassword", user.getPassword());
    }

    @Test
    public void testGetSetVet() {
        Vet vet = new Vet();
        user.setVet(vet);
        Assert.assertEquals(vet, user.getVet());
    }

    @Test
    public void testGetSetOwner() {
        PetOwner owner = new PetOwner();
        user.setOwner(owner);
        Assert.assertEquals(owner, user.getOwner());
    }
}
