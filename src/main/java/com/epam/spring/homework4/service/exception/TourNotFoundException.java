package com.epam.spring.homework4.service.exception;

public class TourNotFoundException extends NotFoundException{

    private static final String MESSAGE = "Tour was not found!";

    public TourNotFoundException() {
        super(MESSAGE);
    }
}
