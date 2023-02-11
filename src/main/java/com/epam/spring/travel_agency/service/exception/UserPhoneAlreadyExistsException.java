package com.epam.spring.travel_agency.service.exception;

public class UserPhoneAlreadyExistsException extends ValidationException{

    private static final String MESSAGE = "User  with this phone already exists";

    public UserPhoneAlreadyExistsException() {
        super(MESSAGE);
    }

}


