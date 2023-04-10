package com.asdc.pawpals.IntegrationTests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.model.Vet;

@RunWith(SpringRunner.class) 
@DataJpaTest 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersistenceTests {

    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    public void persistUser() throws Exception{
        User user = new User();
        user.setUserId("jDoe");
        user.setEmail("jDoe@gmail.com");
        user.setRole("PET_OWNER");
        user.setPassword("root123");
        
        entityManager.persistAndFlush(user);
        entityManager.clear();

        User actual = entityManager.find(User.class, user.getUserId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    public void persistVet() throws Exception{
        Vet vet = new Vet();

        vet.setFirstName("John");
        vet.setLastName("Doe");

        User user = new User();
        user.setUserId("jDoe");
        user.setEmail("jDoe@gmail.com");
        user.setRole("VET");
        user.setPassword("root123");
        entityManager.persistAndFlush(user);
        
        vet.setUser(user);
        Long id = (Long)entityManager.persistAndGetId(vet);
        entityManager.flush();
        entityManager.clear();

        Vet actual = entityManager.find(Vet.class, id);

        assertThat(actual.getUser().getUserId()).isEqualTo(vet.getUser().getUserId());
    }


    @Test
    public void persistPetOwner() throws Exception{
        PetOwner petOwner = new PetOwner();
        petOwner.setFirstName("James");
        petOwner.setLastName("Carles");

        User user = new User();
        user.setUserId("jCarles");
        user.setEmail("jCarles@gmail.com");
        user.setRole("PET_OWNER");
        user.setPassword("root123");
        entityManager.persistAndFlush(user);

        petOwner.setUser(user);
        Long id = (Long)entityManager.persistAndGetId(petOwner);
        entityManager.flush();
        entityManager.clear();

        PetOwner actual = entityManager.find(PetOwner.class, id);

        assertThat(actual.getUser().getUserId()).isEqualTo(petOwner.getUser().getUserId());
    }
}
