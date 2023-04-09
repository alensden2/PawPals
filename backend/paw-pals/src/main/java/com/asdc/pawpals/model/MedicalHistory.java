package com.asdc.pawpals.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MedicalHistory {

    /**
     * The unique identifier for this medical history record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * The date when the ailment was diagnosed.
     */
    private String dateDiagnosed;

    /**
     * The name of the ailment.
     */
    private String ailmentName;

    /**
     * The prescription given for this ailment.
     */
    private String prescription;

    /**
     * The vaccines given to treat this ailment.
     */
    private String vaccines;

    /**
     * The animal associated with this medical history record.
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    private Animal animal;

    /**
     * The vet who diagnosed and treated this ailment.
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    private Vet vet;

}
