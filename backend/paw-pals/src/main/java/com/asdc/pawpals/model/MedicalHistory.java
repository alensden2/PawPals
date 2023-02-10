package com.asdc.pawpals.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class MedicalHistory {

    @Id
    private Long id;
    private String dateDiagnosed;
    private String ailmentName;
    private String prescription;
    private String vaccines;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Animal animal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Vet vet;

}
