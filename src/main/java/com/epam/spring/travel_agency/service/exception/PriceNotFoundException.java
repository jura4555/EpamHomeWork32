package com.epam.spring.travel_agency.service.exception;

public class PriceNotFoundException extends NotFoundException{

    private static final String MESSAGE = "Please update price order";

    public PriceNotFoundException() {
        super(MESSAGE);
    }

}
