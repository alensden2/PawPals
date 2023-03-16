package com.asdc.pawpals.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.asdc.pawpals.service.implementation.AdminReadServiceImpl;

@SpringBootTest
public class AdminControllerTest {
  @Autowired
  AdminController adminController;

  AdminReadServiceImpl adminReadServiceMock;

  @BeforeEach
  public void setup() {
    adminReadServiceMock = mock(AdminReadServiceImpl.class);
    adminController.adminReadService = adminReadServiceMock;
  }

  @Test
  public void objectCreated() {
    assertNotNull(adminController);
  }
}
