package com.asdc.pawpals.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
  @Id
  private String userId;

  private String role; //Pet Owner, Vet, admin
  private String email;
  private String password;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Vet vet;

  @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL})
  private PetOwner owner;
}
