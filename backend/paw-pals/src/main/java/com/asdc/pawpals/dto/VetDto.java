package com.asdc.pawpals.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VetDto extends UserDto {
    private String name;
    private String licenseNumber;
    private String clinicAddress;
    private Integer experience;
    private String qualification;
}
