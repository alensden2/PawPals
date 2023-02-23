package com.asdc.pawpals.dto;

import java.util.List;

public class PetOwnerDto extends UserDto {
    private String phoneNo;
    private String photoUrl;
    private String address;
    private List<AnimalDto> pets;
}
