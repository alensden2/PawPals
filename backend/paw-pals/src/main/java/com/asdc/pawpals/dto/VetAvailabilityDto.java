package com.asdc.pawpals.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.util.Pair;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VetAvailabilityDto {
    private Integer availabilityId;
    private String dayOfWeek;
    List<Pair<String, String>> slots; // [(9,1), (1.30,4.30)....]
    private String vetUserId;
}
