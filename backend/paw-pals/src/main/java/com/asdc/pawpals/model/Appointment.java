package com.asdc.pawpals.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String date;
    private String startTime;
    private String endTime;

    @ManyToOne(cascade = CascadeType.PERSIST)
    Animal animal;

    @ManyToOne(cascade = CascadeType.PERSIST)
    Vet vet;
}
