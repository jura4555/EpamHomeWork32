package com.epam.spring.travel_agency.service.exception;

public class HotelNotFoundException extends NotFoundException{
    private static final String MESSAGE = "Hotel was not found!";

    public HotelNotFoundException() {
        super(MESSAGE);
    }
}
