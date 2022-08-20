package com.epam.spring.homework4.service.exception;

import com.epam.spring.homework4.service.model.enums.ErrorType;
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
