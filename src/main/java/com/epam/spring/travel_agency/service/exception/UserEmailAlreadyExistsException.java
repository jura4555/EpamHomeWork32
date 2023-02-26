package com.epam.spring.travel_agency.service.exception;

public class UserEmailAlreadyExistsException extends ValidationException{

    private static final String MESSAGE = "User  with this email already exists";

    public UserEmailAlreadyExistsException() {
        super(MESSAGE);
    }

}
