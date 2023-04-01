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
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String firstName;
  private String lastName;
  private String licenseNumber;
  private String clinicAddress;
  private Integer experience;
  private String qualification;
  private String profileStatus;
  private String phoneNo;

  @Column(name = "clinic_url", length = 10485760)
  @Lob
  private Byte[] clinicUrl;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "user_userId")
  private User user;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "vet")
  List<Appointment> appointment;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "vet")
  List<VetAvailability> availability;
}
