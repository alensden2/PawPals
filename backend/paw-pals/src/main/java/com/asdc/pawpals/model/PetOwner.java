package com.asdc.pawpals.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class PetOwner {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String photoUrl;
    private String address;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private List<Animal> animals;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

}
