package com.asdc.pawpals.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AnimalDto {
    private String name;
    private String type;
    private Integer age;
    private String gender;
    private PetOwnerDto owner;
    private String photoUrl;
}
