package com.epam.spring.homework4.service.exception;


public class MaxDisCountNotFound extends NotFoundException{

    private static final String MESSAGE = "Please update tour.maxDisCount because maxDisCount dont exactly 0";

    public MaxDisCountNotFound() {
        super(MESSAGE);
    }

}
