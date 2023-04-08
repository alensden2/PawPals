/**

The Vet class represents a veterinary doctor who can perform medical treatments on animals.
*/
package com.asdc.pawpals.model;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vet {

/** The unique ID of this vet */
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private Long id;

/** The first name of this vet */
private String firstName;

/** The last name of this vet */
private String lastName;

/** The license number of this vet */
private String licenseNumber;

/** The address of this vet's clinic */
private String clinicAddress;

/** The years of experience of this vet */
private Integer experience;

/** The qualification of this vet */
private String qualification;

/** The status of this vet's profile */
private String profileStatus;

/** The phone number of this vet */
private String phoneNo;

/** The URL of this vet's clinic */
@Column(name = "clinic_url", length = 10485760)
@Lob
private Byte[] clinicUrl;

/** The user associated with this vet */
@OneToOne(cascade = CascadeType.MERGE)
@JoinColumn(name = "user_userId")
private User user;

/** The list of appointments scheduled for this vet */
@OneToMany(cascade = CascadeType.ALL, mappedBy = "vet")
List<Appointment> appointment;

/** The list of available time slots for this vet */
@OneToMany(cascade = CascadeType.ALL, mappedBy = "vet")
List<VetAvailability> availability;
}
