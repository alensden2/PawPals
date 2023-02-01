package com.asdc.pawpals.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Vet {

    @Id
    private Long id;
    private String licenseNumber;
    private String clinicAddress;
    private Integer experience;
    private String qualification;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "vet")
    private List<MedicalHistory> medicalHistories;

   
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
