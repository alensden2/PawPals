package com.asdc.pawpals.service.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.asdc.pawpals.controller.Test;
import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.model.Animal;
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

  @InjectMocks
  private AdminServiceImpl animalService;

  private Animal animal;

  @Before(value = "")
  public void setUp() {
    objectMapper = new ObjectMapper();
    objectMapper.configure(
      DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
      false
    );
  }

  @Before(value = "")
  public void setup() {
    animal = new Animal();
    animal.setName("Simba");
    animal.setType("Lion");
    animal.setAge(5);
    animal.setGender("Male");
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

  @Test
  public void testDeleteVetSuccess() {
    Long vetId = 1L;
    Vet vet = new Vet();
    vet.setId(vetId);

    when(adminPostVetRepository.findById(vetId)).thenReturn(Optional.of(vet));
    doNothing().when(adminPostVetRepository).delete(vet);

    VetDto vetDto = adminServiceImpl.deleteVet(vetId);

    verify(adminPostVetRepository, times(1)).findById(vetId);
    verify(adminPostVetRepository, times(1)).delete(vet);

    assertNotNull(vetDto);
    //assertEquals(vetDto.getId(), vetId);
  }

  @Test
  public void testDeleteVetNotFound() {
    Long vetId = 1L;

    when(adminPostVetRepository.findById(vetId)).thenReturn(Optional.empty());

    VetDto vetDto = adminServiceImpl.deleteVet(vetId);

    verify(adminPostVetRepository, times(1)).findById(vetId);
    verify(adminPostVetRepository, never()).delete(any(Vet.class));

    assertNull(vetDto);
  }

  @Test
  public void testUpdateVetSuccess() {
    Long vetId = 1L;
    Vet existingVet = new Vet();
    existingVet.setId(vetId);
    existingVet.setName("Old Name");
    existingVet.setLicenseNumber("123456");
    existingVet.setClinicAddress("Old Address");
    existingVet.setExperience(5);
    existingVet.setQualification("Old Qualification");
    existingVet.setUser(new User());

    Vet updatedVet = new Vet();
    updatedVet.setName("New Name");
    updatedVet.setLicenseNumber("654321");
    updatedVet.setClinicAddress("New Address");
    updatedVet.setExperience(10);
    updatedVet.setQualification("New Qualification");

    when(adminPostVetRepository.findById(vetId))
      .thenReturn(Optional.of(existingVet));
    when(adminPostVetRepository.save(any(Vet.class))).thenReturn(updatedVet);

    VetDto vetDto = adminServiceImpl.updateVet(vetId, updatedVet);

    verify(adminPostVetRepository, times(1)).findById(vetId);
    verify(adminPostVetRepository, times(1)).save(any(Vet.class));

    assertNotNull(vetDto);
    assertEquals(vetDto.getName(), "New Name");
    assertEquals(vetDto.getLicenseNumber(), "654321");
    assertEquals(vetDto.getClinicAddress(), "New Address");
    assertEquals(vetDto.getQualification(), "New Qualification");
  }

  @Test
  public void testUpdateAnimalSuccess() {
    Long id = 1L;

    Animal updatedAnimal = new Animal();
    updatedAnimal.setName("Mufasa");
    updatedAnimal.setType("Lion");
    updatedAnimal.setAge(10);
    updatedAnimal.setGender("Male");

    Optional<Animal> optionalAnimal = Optional.of(animal);

    when(adminPostAnimalRepository.findById(id)).thenReturn(optionalAnimal);
    when(adminPostAnimalRepository.save(any(Animal.class)))
      .thenReturn(updatedAnimal);

    AnimalDto animalDto = animalService.updateAnimal(id, updatedAnimal);

    verify(adminPostAnimalRepository, times(1)).findById(id);
    verify(adminPostAnimalRepository, times(1)).save(any(Animal.class));

    assertNotNull(animalDto);
    assertEquals(animalDto.getName(), "Mufasa");
    assertEquals(animalDto.getType(), "Lion");
    assertEquals(animalDto.getGender(), "Male");
  }
}
