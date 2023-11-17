package com.epam.spring.travel_agency.service.exception;

public class UserLoginAlreadyExistsException extends ValidationException{

    private static final String MESSAGE = "User  with this login already exists";

    public UserLoginAlreadyExistsException() {
        super(MESSAGE);
    }
}
