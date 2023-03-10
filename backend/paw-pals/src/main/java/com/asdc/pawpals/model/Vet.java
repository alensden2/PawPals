package com.asdc.pawpals.model;

import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Vet {

    @Id
    private Long id;
    private String name;
    private String licenseNumber;
    private String clinicAddress;
    private Integer experience;
    private String qualification;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "vet")
    private List<MedicalHistory> medicalHistories;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "vet")
    List<Appointment> appointment;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "vet")
    List<VetAvailability> availability;
}
