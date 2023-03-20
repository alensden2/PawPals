package com.asdc.pawpals.model;

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
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PetOwner {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String firstName;
  private String lastName;
  private String phoneNo;

  @Column(name = "photo_url", length = 10485760)
  @Lob
  private Byte[] photoUrl;

  private String address;

//  @OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE}  ,orphanRemoval = true, mappedBy = "owner")
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
  private List<Animal> animals;

  @OneToOne
  @JoinColumn(name = "user_userId")
  private User user;
}
