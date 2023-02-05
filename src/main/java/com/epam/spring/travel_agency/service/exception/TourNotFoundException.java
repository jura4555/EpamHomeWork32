package com.epam.spring.travel_agency.service.exception;

public class TourNotFoundException extends NotFoundException{

    private static final String MESSAGE = "Tour was not found!";

    public TourNotFoundException() {
        super(MESSAGE);
    }
}
