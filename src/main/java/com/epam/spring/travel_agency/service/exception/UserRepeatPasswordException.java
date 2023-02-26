package com.epam.spring.travel_agency.service.exception;

public class UserRepeatPasswordException extends ValidationException{

    private static final String MESSAGE = "User password and repeat password is not same";

    public UserRepeatPasswordException() {
        super(MESSAGE);
    }
}
