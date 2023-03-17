package com.asdc.pawpals.model;

import java.util.List;

import jakarta.persistence.*;
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

    @Column(name = "photo_url", length = 10485760)
    @Lob
    private Byte[] photoUrl;

    private String address;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private List<Animal> animals;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

}
