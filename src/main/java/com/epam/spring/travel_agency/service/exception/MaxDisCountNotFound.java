package com.epam.spring.travel_agency.service.exception;


public class MaxDisCountNotFound extends NotFoundException{

    private static final String MESSAGE = "Please update tour.maxDisCount because maxDisCount dont exactly 0";

    public MaxDisCountNotFound() {
        super(MESSAGE);
    }

}
