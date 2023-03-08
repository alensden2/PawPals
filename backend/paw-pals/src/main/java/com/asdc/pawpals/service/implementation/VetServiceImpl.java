package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.repository.VetRepository;
import com.asdc.pawpals.service.VetService;
import com.asdc.pawpals.utils.Transformations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class VetServiceImpl implements VetService {

    @Autowired
    VetRepository vetRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public Boolean registerVet(VetDto vetDto){
        Boolean vetRegistered = false;
        if(vetDto != null){
            Vet vet = Transformations.DTO_TO_MODEL_CONVERTER.vet(vetDto);
            //check if user with given username exists, then only register that user as vet
            Optional<User> user = userRepository.findById(vetDto.getUsername());
            if(!user.isEmpty()){
                Long oldCount = vetRepository.count();
                vet.setId(oldCount+1);
                vetRepository.save(vet);
                Long newCount = vetRepository.count();
                vetRegistered = ((oldCount + 1) == newCount);
            }
            else{
                throw new UsernameNotFoundException("Please provide a valid username");
            }
        }
        return vetRegistered;
    }

}
