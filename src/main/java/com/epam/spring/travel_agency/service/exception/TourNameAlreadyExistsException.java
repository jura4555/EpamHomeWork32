package com.epam.spring.travel_agency.service.exception;

public class TourNameAlreadyExistsException extends ValidationException{

    private static final String MESSAGE = "Tour  with this name already exists";

    public TourNameAlreadyExistsException() {
        super(MESSAGE);
    }
}
