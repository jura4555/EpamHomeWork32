package com.epam.spring.homework4.service.exception;

public class PriceNotFoundException extends NotFoundException{

    private static final String MESSAGE = "Please update price order";

    public PriceNotFoundException() {
        super(MESSAGE);
    }

}
