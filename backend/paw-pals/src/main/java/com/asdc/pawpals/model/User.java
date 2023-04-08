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

  /**
   * The unique ID of the user.
   */
  @Id
  private String userId;

  /**
   * The role of the user: "Pet Owner", "Vet", or "admin".
   */
  private String role;

  /**
   * The email address of the user.
   */
  private String email;

  /**
   * The hashed password of the user.
   */
  private String password;

  /**
   * The vet associated with the user, if any.
   */
  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Vet vet;

  /**
   * The pet owner associated with the user, if any.
   */
  @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL})
  private PetOwner owner;
}
