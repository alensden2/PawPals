package com.asdc.pawpals.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetOwner {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String photoUrl;
    private String address;

}
