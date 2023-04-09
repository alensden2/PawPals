/**
 * This class represents an appointment for an animal with a veterinarian.
 */
package com.asdc.pawpals.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Appointment {

    /**
     * The unique identifier for the appointment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    /**
     * The date of the appointment.
     */
    private String date;

    /**
     * The start time of the appointment.
     */
    private String startTime;

    /**
     * The end time of the appointment.
     */
    private String endTime;

    /**
     * The status of the appointment. Can be "PENDING", "CONFIRMED", or "REJECTED".
     */
    @Column
    private String status = "PENDING";

    /**
     * The animal associated with the appointment.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    Animal animal;

    /**
     * The veterinarian associated with the appointment.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    Vet vet;
}
