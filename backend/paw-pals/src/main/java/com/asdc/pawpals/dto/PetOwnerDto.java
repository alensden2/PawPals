package com.asdc.pawpals.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PetOwnerDto extends UserDto {
    private String phoneNo;
    private String photoUrl;
    private String address;
    private List<AnimalDto> pets;

}
