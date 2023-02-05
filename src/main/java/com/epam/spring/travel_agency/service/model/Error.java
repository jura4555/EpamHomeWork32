package com.epam.spring.travel_agency.service.model;

import com.epam.spring.travel_agency.service.model.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Error {
    String message;
    ErrorType errorType;
    LocalDateTime timeStamp;
}
