package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.PetOwnerDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.exception.InvalidAnimalId;
import com.asdc.pawpals.exception.InvalidVetID;
import com.asdc.pawpals.exception.PetOwnerAlreadyDoesNotExists;
import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.repository.AnimalRepository;
import com.asdc.pawpals.repository.PetOwnerRepository;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.repository.VetRepository;
import com.asdc.pawpals.utils.Transformations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTest {
    @Mock
    private VetRepository vetRepository;

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private Transformations transformations;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PetOwnerRepository petOwnerRepository;


    @InjectMocks
    private AdminServiceImpl adminServiceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

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
            assertEquals(
                    animals.get(i).getOwner().getUser().getUserId(),
                    animalsDto.get(i).getOwnerId()
            );
        }
    }

    @Test
    public void testGetAllVetRecords() {
        // Arrange
        List<Vet> vets = new ArrayList<>();
        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Dr. Smith");
        vet1.setQualification("Dentistry");

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Dr. Patel");
        vet2.setQualification("Cardiology");

        vets.add(vet1);
        vets.add(vet2);

        when(vetRepository.findAll()).thenReturn(vets);

        // Act
        List<VetDto> vetsDto = adminServiceImpl.getAllVetRecords();

        // Assert
        assertNotNull(vetsDto);
        assertEquals(vets.size(), vetsDto.size());

        for (int i = 0; i < vetsDto.size(); i++) {
            assertEquals(vets.get(i).getFirstName(), vetsDto.get(i).getFirstName());
            assertEquals(
                    vets.get(i).getQualification(),
                    vetsDto.get(i).getQualification()
            );
        }
    }

    @Test
    public void testGetAllPetOwnerRecords() {
        // Arrange
        List<PetOwner> petOwners = new ArrayList<>();
        PetOwner petOwner1 = new PetOwner();
        petOwner1.setId(1L);
        petOwner1.setFirstName("John");
        petOwner1.setLastName("Doe");
        petOwner1.setAddress("123 Main St");
        petOwner1.setPhoneNo("555-1234");
        petOwner1.setUser(new User());

        PetOwner petOwner2 = new PetOwner();
        petOwner2.setId(2L);
        petOwner2.setFirstName("Jane");
        petOwner2.setLastName("Doe");
        petOwner2.setAddress("456 Oak St");
        petOwner2.setPhoneNo("555-5678");
        petOwner2.setUser(new User());

        petOwners.add(petOwner1);
        petOwners.add(petOwner2);

        when(petOwnerRepository.findAll()).thenReturn(petOwners);

        // Act
        List<PetOwnerDto> petOwnerDtos = adminServiceImpl.getAllPetOwnerRecords();

        // Assert
        assertNotNull(petOwnerDtos);
        assertEquals(petOwners.size(), petOwnerDtos.size());

        for (int i = 0; i < petOwnerDtos.size(); i++) {
            assertEquals(
                    petOwners.get(i).getFirstName(),
                    petOwnerDtos.get(i).getFirstName()
            );
            assertEquals(
                    petOwners.get(i).getLastName(),
                    petOwnerDtos.get(i).getLastName()
            );
            assertEquals(
                    petOwners.get(i).getAddress(),
                    petOwnerDtos.get(i).getAddress()
            );
            assertEquals(
                    petOwners.get(i).getPhoneNo(),
                    petOwnerDtos.get(i).getPhoneNo()
            );
        }
    }

    @Test
    public void testAddAnimal() throws PetOwnerAlreadyDoesNotExists {
        // Arrange
        Animal animal = new Animal();
        animal.setName("Fluffy");
        PetOwner petOwner = new PetOwner();
        petOwner.setFirstName("John");
        petOwner.setLastName("Doe");
        User user = new User();
        user.setUserId("johndoe");
        user.setPassword("password");
        petOwner.setUser(user);
        animal.setOwner(petOwner);
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
        when(petOwnerRepository.count()).thenReturn(1L);
        when(animalRepository.save(any(Animal.class))).thenReturn(animal);

        // Act
        AnimalDto returnedDto = adminServiceImpl.addAnimal(animal);

        // Assert
        assertNotNull(returnedDto);
        assertEquals("Fluffy", returnedDto.getName());
    }


    @Test
    public void testAddVet() throws UserNameNotFound { // fix
        Vet vet = new Vet();
        User user = new User();
        user.setUserId("vet@example.com");
        user.setRole("Vet");
        user.setEmail("vet@example.com");
        user.setPassword("password");
        vet.setUser(user);
        vet.setFirstName("John");
        vet.setLastName("Doe");
        vet.setLicenseNumber("1234567890");
        vet.setClinicAddress("123 Main St");
        vet.setExperience(5);
        vet.setQualification("DVM");
        vet.setProfileStatus("Active");
        vet.setPhoneNo("555-555-5555");
        VetDto vetDto = null;
        when(vetRepository.save(any(Vet.class))).thenReturn(vet);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        vetDto = adminServiceImpl.addVet(vet);
        assertNotNull(vetDto);
        assertNotNull(vetDto.getUsername());
        assertEquals(vet.getFirstName(), vetDto.getFirstName());
        assertEquals(vet.getLastName(), vetDto.getLastName());
        assertEquals(vet.getLicenseNumber(), vetDto.getLicenseNumber());

    }

    @Test
    public void testUpdateVet() throws InvalidVetID {
        // create a sample Vet object to update with
        Vet updatedVet = new Vet();
        updatedVet.setFirstName("John");
        updatedVet.setLastName("Doe");
        updatedVet.setLicenseNumber("123456789");
        updatedVet.setClinicAddress("123 Main St");
        updatedVet.setExperience(5);
        updatedVet.setQualification("Doctor of Veterinary Medicine");
        updatedVet.setUser(new User());

        // create a sample Vet object to update
        Vet existingVet = new Vet();
        existingVet.setId(1L);
        existingVet.setFirstName("Jane");
        existingVet.setLastName("Doe");
        existingVet.setLicenseNumber("987654321");
        existingVet.setClinicAddress("456 Elm St");
        existingVet.setExperience(10);
        existingVet.setQualification("Doctor of Veterinary Medicine");
        existingVet.setUser(new User());

        // mock the repository's findById method to return the existing Vet object
        when(vetRepository.findById(1L)).thenReturn(Optional.of(existingVet));

        // call the updateVet method
        VetDto result = adminServiceImpl.updateVet(1L, updatedVet);

        // verify that the existing Vet object was updated correctly
        assertEquals("John", existingVet.getFirstName());
        assertEquals("Doe", existingVet.getLastName());
        assertEquals("123456789", existingVet.getLicenseNumber());
        assertEquals("123 Main St", existingVet.getClinicAddress());
        assertEquals(
                "Doctor of Veterinary Medicine",
                existingVet.getQualification()
        );

        // verify that the returned VetDto object contains the updated values
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("123456789", result.getLicenseNumber());
        assertEquals("123 Main St", result.getClinicAddress());
        assertEquals("Doctor of Veterinary Medicine", result.getQualification());
    }


    @Test
    public void testDeleteVet() throws InvalidVetID {
        // create a new vet
        Vet vet = new Vet();
        // set the vet properties
        vet.setFirstName("John");
        vet.setLastName("Doe");
        vet.setLicenseNumber("12345");
        vet.setClinicAddress("123 Main St.");
        vet.setExperience(5);
        vet.setQualification("DVM");
        vet.setUser(new User());

        //Mock
        doNothing().when(vetRepository).delete(any(Vet.class));
        when(vetRepository.findById(any())).thenReturn(Optional.of(vet));

        // delete the vet
        VetDto vetDto = adminServiceImpl.deleteVet(vet.getId());

        // verify that the vet was deleted
        assertNotNull(vetDto);
        assertEquals(vetDto.getFirstName(),vet.getFirstName());
        assertEquals(vetDto.getLastName(),vet.getLastName());
        assertEquals(vetDto.getClinicUrl(),vet.getClinicUrl());
    }

    @Test
    public void testDeleteAnimal() throws InvalidAnimalId {
        // create a new animal
        Animal animal = new Animal();
        // set the animal properties
        animal.setName("Fluffy");
        animal.setType("Cat");
        animal.setAge(3);
        animal.setGender("Female");
        PetOwner owner=new PetOwner();
        owner.setUser(new User());
        animal.setOwner(owner);
        animal.setPhotoUrl(new Byte[]{1, 2, 3});
        doNothing().when(animalRepository).delete(any(Animal.class));
        when(animalRepository.findById(any())).thenReturn(Optional.of(animal));
        // delete the animal
        AnimalDto animalDto = adminServiceImpl.deleteAnimal(animal.getId());

        // verify that the animal was deleted
        assertNotNull(animalDto);
        assertEquals(animalDto.getName(),animal.getName());
        assertEquals(animalDto.getType(),animal.getType());
        assertEquals(animalDto.getAge(),animal.getAge());
    }


    @Test
    public void testUpdateAnimal() throws InvalidAnimalId {

        Animal animal = new Animal();
        PetOwner petOwner = new PetOwner();
        petOwner.setUser(new User());
        animal.setOwner(petOwner);
        animal.setId(1l);
        animal.setName("tom");
        animal.setType("dog");
        when(animalRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(animal));
        AnimalDto animalDto = adminServiceImpl.updateAnimal(1l, animal);
        assertNotNull(animalDto);
        assertNotNull(animalDto.getId());
        assertNotNull(animalDto.getName());
        assertNotNull(animalDto.getType());
        assertEquals(animal.getName(), animalDto.getName());
        assertEquals(animal.getId(), animalDto.getId());
        assertEquals(animal.getType(), animalDto.getType());


    }

}
