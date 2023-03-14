package com.asdc.pawpals.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.asdc.pawpals.dto.VetAvailabilityDto;
import com.asdc.pawpals.dto.VetDto;
import com.asdc.pawpals.dto.VetScheduleDto;

@Service
public interface VetService {

    public Boolean registerVet(VetDto vet);
    public List<VetAvailabilityDto> getVetAvailability(String userId);
    public List<VetAvailabilityDto> getVetAvailability(Long vetId);
    public VetAvailabilityDto getVetAvailabilityOnSpecificDay(String userId, String date);
    public VetAvailabilityDto getVetAvailabilityOnSpecificDay(Long vetId, String date);
    public VetScheduleDto getVetScheduleOnSpecificDay(String userId, String date);
}
