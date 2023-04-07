package com.asdc.pawpals.controller;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.asdc.pawpals.service.implementation.AdminServiceImpl;
@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {
  @Mock
  AdminServiceImpl adminServiceImpl;

  @InjectMocks
  AdminController adminController;

  @Mock
  ApiResponse apiResponse;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
}

@Test
public void objectCreated() {
    assertNotNull(adminServiceImpl);
    assertNotNull(adminController);
    assertNotNull(apiResponse);
}
  
}
