package com.epam.spring.travel_agency.service.exception;

import com.epam.spring.travel_agency.service.model.enums.ErrorType;

public class NotFoundException extends TypeException {
    public NotFoundException(String message){
        super(message, ErrorType.VALIDATION_ERROR_TYPE);
    }
}
