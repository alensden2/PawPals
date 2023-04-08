package com.asdc.pawpals.service.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.repository.AnimalRepository;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.repository.VetRepository;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTest {
  @Mock
  private VetRepository vetRepository;

  @Mock
  private AnimalRepository animalRepository;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private AdminServiceImpl adminServiceImpl;

  @Test
  public void testGetAllAnimalRecords() {
      // Arrange
      List<Animal> animals = new ArrayList<>();
      Animal animal1 = new Animal();
      animal1.setId(1L);
      animal1.setName("Milo");
      animal1.setType("Dog");
      animal1.setAge(5);
      animal1.setGender("Male");
      animal1.setOwner(new PetOwner());
      
      Animal animal2 = new Animal();
      animal2.setId(2L);
      animal2.setName("Whiskers");
      animal2.setType("Cat");
      animal2.setAge(3);
      animal2.setGender("Female");
      PetOwner p2 = new PetOwner();
      User u2 = new User();
      u2.setUserId("345");
      p2.setUser(u2);
      animal2.setOwner(p2);
      
      PetOwner p1 = new PetOwner();
      User u1 = new User();
      u1.setUserId("123");
      p1.setUser(u1);
      animal1.setOwner(p1);
      
      animals.add(animal1);
      animals.add(animal2);
      
      when(animalRepository.findAll()).thenReturn(animals);
      
      // Act
      List<AnimalDto> animalsDto = adminServiceImpl.getAllAnimalRecords();
      
      // Assert
      assertNotNull(animalsDto);
      assertEquals(animals.size(), animalsDto.size());
      
      for (int i = 0; i < animalsDto.size(); i++) {
          assertEquals(animals.get(i).getId(), animalsDto.get(i).getId());
          assertEquals(animals.get(i).getName(), animalsDto.get(i).getName());
          assertEquals(animals.get(i).getType(), animalsDto.get(i).getType());
          assertEquals(animals.get(i).getAge(), animalsDto.get(i).getAge());
          assertEquals(animals.get(i).getGender(), animalsDto.get(i).getGender());
          assertEquals(animals.get(i).getOwner().getUser().getUserId(), animalsDto.get(i).getOwnerId());
      }
  }

  
  


}
  
  
  
  
  

