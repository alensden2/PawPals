package com.asdc.pawpals.dto;

import java.util.List;

import org.springframework.data.util.Pair;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VetScheduleDto {
    private String vetUserId;
    List<Pair<String, String>> slotsBooked; // [(9,1), (1.30,4.30)....]
}


