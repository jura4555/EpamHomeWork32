package com.epam.spring.homework4.service.exception;

public class OrderNotFoundException extends NotFoundException{
    private static final String MESSAGE = "Order was not found!";

    public OrderNotFoundException() {
        super(MESSAGE);
    }
}
