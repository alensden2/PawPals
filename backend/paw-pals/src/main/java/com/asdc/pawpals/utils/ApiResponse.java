package com.asdc.pawpals.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * ApiResponse is a model class that represents a generic response for API requests.
 * It contains the response message, an error flag indicating if there was an error or not, the response body and a success flag indicating if the request was successful or not.
 */
@Component
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean error;
    private Object body;
    private boolean success;

}
