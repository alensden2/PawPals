package com.asdc.pawpals.service.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.asdc.pawpals.controller.Test;
import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.exception.PetOwnerAlreadyDoesNotExists;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.repository.AdminPostAnimalRepository;
import com.asdc.pawpals.repository.AdminPostVetRepository;
import com.asdc.pawpals.repository.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.aspectj.lang.annotation.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTest {
  @Mock
  private AdminPostVetRepository adminPostVetRepository;

  @Mock
  private AdminPostAnimalRepository adminPostAnimalRepository;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private AdminServiceImpl adminServiceImpl;

  private ObjectMapper objectMapper;

  @Before(value = "")
  public void setUp() {
    objectMapper = new ObjectMapper();
    objectMapper.configure(
      DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
      false
    );
  }

  @Test
  public void testAddVetSuccess() {
    Vet vet = new Vet();
    User user = new User();
    user.setUserId(String.valueOf(1L));
    vet.setUser(user);

    when(userRepository.save(any(User.class))).thenReturn(user);

    when(adminPostVetRepository.save(any(Vet.class))).thenReturn(vet);

    VetDto vetDto = adminServiceImpl.addVet(vet);

    verify(userRepository, times(1)).save(any(User.class));

    verify(adminPostVetRepository, times(1)).save(any(Vet.class));

    assertNotNull(vetDto);
  }
  //   @Test
  //   public void testAddAnimalSuccess() throws PetOwnerAlreadyDoesNotExists {
  //     Animal animal = new Animal();
  //     User user = new User();
  //     user.setUserId(String.valueOf(1L));
  //     PetOwner owner = new Owner();
  //     owner.setUserId(user.getUserId());
  //     owner.setFirstName("John");
  //     owner.setLastName("Doe");

  //     when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

  //     when(adminPostAnimalRepository.save(any(Animal.class))).thenReturn(animal);

  //     AnimalDto animalDto = adminServiceImpl.addAnimal(animal);

  //     verify(userRepository, times(1)).findById(anyLong());

  //     verify(adminPostAnimalRepository, times(1)).save(any(Animal.class));

  //     assertNotNull(animalDto);

  //     assertEquals(animalDto.getOwner().getUserId(), owner.getUserId());
  //     equals(animal);

}
