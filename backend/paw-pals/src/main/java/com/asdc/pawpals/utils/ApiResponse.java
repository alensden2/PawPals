package com.asdc.pawpals.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class ApiResponse {
    private String message;
    private boolean error;
    private Object body;
    private boolean success;

}
