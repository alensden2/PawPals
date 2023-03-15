package com.asdc.pawpals.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.asdc.pawpals.dto.VetAvailabilityDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.model.Appointment;
import com.asdc.pawpals.model.User;
import com.asdc.pawpals.model.Vet;
import com.asdc.pawpals.model.VetAvailability;
import com.asdc.pawpals.repository.AppointmentRepository;
import com.asdc.pawpals.repository.UserRepository;
import com.asdc.pawpals.repository.VetAvailabilityRepository;
import com.asdc.pawpals.repository.VetRepository;
import com.asdc.pawpals.service.VetService;
import com.asdc.pawpals.utils.CommonUtils;
import com.asdc.pawpals.utils.Transformations;

@Component
@Lazy
public class VetServiceImpl implements VetService {

    @Autowired
    VetRepository vetRepository;

    @Autowired
    VetAvailabilityRepository vetAvailabilityRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

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

    @Override
    public List<VetAvailabilityDto> getVetAvailability(String userId){
        List<VetAvailability> availability =  vetAvailabilityRepository.findByVet_User_UserId(userId);
        List<VetAvailabilityDto> availabilityDto = null;
        if(availability != null){
            availabilityDto = availability.stream().map(Transformations.MODEL_TO_DTO_CONVERTER::vetAvailability).collect(Collectors.toList());
        }
        return availabilityDto;
    }

    @Override
    public List<VetAvailabilityDto> getVetAvailability(Long vetId){
        List<VetAvailability> availability =  vetAvailabilityRepository.findByVetId(vetId);
        List<VetAvailabilityDto> availabilityDto = null;
        if(availability != null){
            availabilityDto = availability.stream().map(Transformations.MODEL_TO_DTO_CONVERTER::vetAvailability).collect(Collectors.toList());
        }
        return availabilityDto;
    }

    @Override
    public VetAvailabilityDto getVetAvailabilityOnSpecificDay(String userId, String date){
        List<VetAvailability> availability =  vetAvailabilityRepository.findByVet_User_UserId(userId);
        List<Appointment> appointments = appointmentRepository.findByVet_User_UserId(userId);
        VetAvailabilityDto availabilityDto = null;
        if(availability != null){
            //get the original vet availability on the specific day mentioned (derived from date given)
            availabilityDto = availability.stream().map(Transformations.MODEL_TO_DTO_CONVERTER::vetAvailability)
            .filter(avl->avl.getDayOfWeek().equals(CommonUtils.getDayFromDate(date))).findFirst().orElse(null);

            if(availabilityDto != null){
                List<Pair<String, String>> freeSlots = new ArrayList<>();
                String starTime = availabilityDto.getSlots().get(0).getFirst();
                String endTime = availabilityDto.getSlots().get(0).getSecond();
                String effectiveLastSlot = CommonUtils.getPreviousSlotTime(endTime);

                String currentSlot = starTime;
                while(!currentSlot.equals(effectiveLastSlot)){
                    final String fCurrSlot = currentSlot;
                    Boolean appointmentBooked = appointments.stream().filter(Objects::nonNull)
                        .filter(apt->apt.getDate().equals(date))
                        .anyMatch(apt->{
                            return apt.getStartTime().equals(fCurrSlot);
                        });
                    if(!appointmentBooked){
                        freeSlots.add(Pair.of(currentSlot, CommonUtils.getNextSlotTime(currentSlot)));
                    }
                    currentSlot = CommonUtils.getNextSlotTime(currentSlot);
                }

                availabilityDto.setSlots(freeSlots);

                // appointments.stream().filter(Objects::nonNull)
                //     .filter(apt-> apt.getDate().equals(date)).forEach(appointment->{
                //             //1. assuming that appointments were booked by checking no conflicts
                //             //2. assuming that appointments are sorted in increasing order of start time
                //             //3. assuming that each appointment is only 30 mins long
                            
                //     });

            }
        }
        return availabilityDto;
    }

    @Override
    public VetAvailabilityDto getVetAvailabilityOnSpecificDay(Long vetId, String date){
        return null;
    }

}
