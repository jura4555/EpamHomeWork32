package com.epam.spring.travel_agency.service.exception;

import com.epam.spring.travel_agency.service.model.enums.ErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class TypeException extends RuntimeException{

    ErrorType errorType;

    public TypeException(String message, ErrorType errorType){
        super(message);
        this.errorType = errorType;
    }
}
