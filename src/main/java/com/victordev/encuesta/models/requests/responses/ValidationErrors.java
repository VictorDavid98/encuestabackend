package com.victordev.encuesta.models.requests.responses;

import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationErrors {
    private Map<String, String> errors;
    private Date timestamp;
}
