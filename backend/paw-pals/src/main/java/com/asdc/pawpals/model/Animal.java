package com.asdc.pawpals.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
  private List<MedicalHistory> medicalHistories;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
  List<Appointment> appointment;

  @Column(name = "photo_url", length = 10485760)
  @Lob
  private Byte[] photoUrl;
}
