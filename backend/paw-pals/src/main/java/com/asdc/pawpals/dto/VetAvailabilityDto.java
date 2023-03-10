package com.asdc.pawpals.dto;

import java.util.List;

import org.springframework.data.util.Pair;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VetAvailabilityDto {
    private Integer availabilityId;
    private String dayOfWeek;
    List<Pair<String, String>> slots; // [(9,1), (1.30,4.30)....]
    private String vetUserId;
}
