package com.asdc.pawpals.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryDtoInline extends MedicalHistoryDto {
    VetDto vet;
}
