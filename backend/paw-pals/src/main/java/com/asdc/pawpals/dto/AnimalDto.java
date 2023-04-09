package com.asdc.pawpals.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class AnimalDto {
    private Long id;
    private String name;
    private String type;
    private Integer age;
    private String gender;
    private String ownerId;
    private Byte[] photoUrl;
    List<MedicalHistoryDto> medicalHistory;

}
