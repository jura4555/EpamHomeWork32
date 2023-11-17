package com.epam.spring.travel_agency.service.exception;

import com.epam.spring.travel_agency.service.model.enums.ErrorType;

public class ValidationException extends TypeException{
    public ValidationException(String message) {
        super(message, ErrorType.VALIDATION_ERROR_TYPE);
    }
}