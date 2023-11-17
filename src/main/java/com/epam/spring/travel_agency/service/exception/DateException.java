package com.epam.spring.travel_agency.service.exception;

public class DateException extends ValidationException{

    private static final String MESSAGE = "Date departure and date arrival specified incorrectly";

    public DateException() {
        super(MESSAGE);
    }
}
