/**
 * This class represents an animal in the system.
 */
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
public class Animal {
    /** The unique identifier of the animal */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /** The name of the animal */
    private String name;

    /** The type of the animal */
    private String type;

    /** The age of the animal */
    private Integer age;

    /** The gender of the animal */
    private String gender;

    /** The owner of the animal */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PetOwner owner;

    /** The medical histories of the animal */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
    private List<MedicalHistory> medicalHistories;

    /** The appointments of the animal */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
    List<Appointment> appointment;

    /** The URL of the photo of the animal */
    @Column(name = "photo_url", length = 10485760)
    @Lob
    private Byte[] photoUrl;
}