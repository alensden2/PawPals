package com.asdc.pawpals.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter @NoArgsConstructor

public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String type;
    private Integer age;
    private String gender;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private PetOwner owner;

    @Column(name = "photo_url", length = 10485760)
    @Lob
    private Byte[] photoUrl;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "animal")
    private List<MedicalHistory> medicalHistories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
    List<Appointment> appointment;


}
