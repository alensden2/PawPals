package com.asdc.pawpals.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PetOwner {
    /**
     * The unique identifier for each PetOwner.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * The first name of the PetOwner.
     */
    private String firstName;
    /**
     * The last name of the PetOwner.
     */
    private String lastName;
    /**
     * The phone number of the PetOwner.
     */
    private String phoneNo;
    /**
     * The photo URL of the PetOwner.
     */
    @Column(name = "photo_url", length = 10485760)
    @Lob
    private Byte[] photoUrl;
    /**
     * The address of the PetOwner.
     */
    private String address;
    /**
     * The list of animals owned by the PetOwner.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Animal> animals;
    /**
     * The User associated with the PetOwner.
     */
    @OneToOne
    @JoinColumn(name = "user_userId")
    private User user;
}