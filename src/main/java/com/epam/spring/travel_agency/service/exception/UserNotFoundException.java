package com.epam.spring.travel_agency.service.exception;

public class UserNotFoundException extends NotFoundException{

    private static final String MESSAGE = "User was not found!";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
