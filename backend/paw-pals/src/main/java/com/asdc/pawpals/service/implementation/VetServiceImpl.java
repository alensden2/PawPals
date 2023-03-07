package com.asdc.pawpals.service.implementation;

import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.repository.VetRepository;
import com.asdc.pawpals.service.VetService;
import com.asdc.pawpals.utils.Transformations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class VetServiceImpl implements VetService {

    @Autowired
    VetRepository vetRepository;


    @Override
    public Boolean registerVet(VetDto vetDto){
        Boolean vetRegistered = false;
        if(vetDto != null){
            Vet vet = Transformations.DTO_TO_MODEL_CONVERTER.vet(vetDto);
            Long oldCount = vetRepository.count();
            vet.setId(oldCount+1);
            vetRepository.save(vet);
            Long newCount = vetRepository.count();
            vetRegistered = ((oldCount + 1) == newCount);
        }
        return vetRegistered;
    }

}
