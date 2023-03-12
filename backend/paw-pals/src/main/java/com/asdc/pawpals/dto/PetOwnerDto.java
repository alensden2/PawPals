package com.asdc.pawpals.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetOwnerDto extends UserDto {
    private String phoneNo;
    private String photoUrl;
    private String address;
    private List<AnimalDto> pets;

}
