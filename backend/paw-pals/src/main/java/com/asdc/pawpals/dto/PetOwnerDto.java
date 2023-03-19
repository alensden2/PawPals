package com.asdc.pawpals.dto;

import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PetOwnerDto extends UserDto {
  private String phoneNo;
  private Byte[] photoUrl;
  private String address;
  private List<AnimalDto> pets;
}
