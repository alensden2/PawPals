package com.asdc.pawpals.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class PetOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
