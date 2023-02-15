package com.epam.spring.travel_agency.service.exception;


public class MaxDisCountNotFound extends NotFoundException{

    private static final String MESSAGE = "Please update tour.maxDisCount because maxDisCount can't be equal to 0";

    public MaxDisCountNotFound() {
        super(MESSAGE);
    }

}
