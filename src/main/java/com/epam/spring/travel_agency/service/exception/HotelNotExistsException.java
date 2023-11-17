package com.epam.spring.travel_agency.service.exception;

public class HotelNotExistsException extends ValidationException{

    private static final String MESSAGE = "Please add hotel to this tour";

    public HotelNotExistsException() {
        super(MESSAGE);
    }
}
