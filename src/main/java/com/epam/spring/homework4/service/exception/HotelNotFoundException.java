package com.epam.spring.homework4.service.exception;

public class HotelNotFoundException extends NotFoundException{
    private static final String MESSAGE = "Hotel was not found!";

    public HotelNotFoundException() {
        super(MESSAGE);
    }
}
