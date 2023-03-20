package com.asdc.pawpals.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.asdc.pawpals.model.MedicalHistory;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class AnimalDto {
    private String name;
    private String type;
    private Integer age;
    private String gender;
    private String ownerId;
    private Byte[] photoUrl;
    List<MedicalHistoryDto> medicalHistory;

}
