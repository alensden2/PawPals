/**

The VetAvailability class represents the availability of a vet for appointments.
*/
package com.asdc.pawpals.model;
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
public class VetAvailability {
/**
* The unique identifier of the vet's availability.
*/
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private Integer id;
/**
 * The day of the week for which the vet is available.
 */
private String dayOfWeek;

/**
 * The start time of the vet's availability.
 */
private String startTime;

/**
 * The end time of the vet's availability.
 */
private String endTime;

/**
 * The vet who is available at the specified time.
 */
@ManyToOne
Vet vet;
}