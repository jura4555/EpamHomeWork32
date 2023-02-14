package com.epam.spring.travel_agency.service.exception;

public class HotelNotExistsExcepton extends ValidationException{

    private static final String MESSAGE = "Please add hotel to this tour";

    public HotelNotExistsExcepton() {
        super(MESSAGE);
    }
}
