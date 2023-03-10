package com.asdc.pawpals.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentDto {
    private Integer id;
    private String date;
    private String startTime;
    private String endTime;
    private String vetUserId;
    private Long animalId; 
}
