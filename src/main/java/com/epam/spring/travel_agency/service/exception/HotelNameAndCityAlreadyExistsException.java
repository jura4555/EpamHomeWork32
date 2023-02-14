package com.epam.spring.travel_agency.service.exception;

public class HotelNameAndCityAlreadyExistsException extends ValidationException{

    private static final String MESSAGE = "Hotel  with this name and city already exists";

    public HotelNameAndCityAlreadyExistsException() {
        super(MESSAGE);
    }
}
