package com.epam.spring.homework4.service.model;

import com.epam.spring.homework4.service.model.enums.TourStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
    private int id;
    private User user;
    private Tour tour;
    private double price;
    private int stepDisCount;
    private int disCount;
    private TourStatus tourStatus;
    private String description;
}
