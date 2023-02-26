package com.epam.spring.travel_agency.service.exception;

public class OrderStatusException extends ValidationException{

    private static final String MESSAGE = "Your can't change status";

    public OrderStatusException() {
        super(MESSAGE);
    }
}
