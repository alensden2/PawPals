package com.asdc.pawpals.service;

import java.util.List;

import com.asdc.pawpals.dto.AnimalDto;
import com.asdc.pawpals.dto.UserDto;
import com.asdc.pawpals.dto.VetDto;

public interface AdminReadService {
  public List<AnimalDto> getAllAnimalRecords();

  public List<VetDto> getAllVetRecords();

  public List<UserDto> getAllUserRecords();

  public Boolean addAnimal(AnimalDto animal);
}
