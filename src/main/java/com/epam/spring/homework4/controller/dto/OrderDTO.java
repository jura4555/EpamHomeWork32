package com.epam.spring.homework4.controller.dto;

import com.epam.spring.homework4.service.model.Tour;
import com.epam.spring.homework4.service.model.User;
import com.epam.spring.homework4.service.model.enums.TourStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {
    private int id;
    private User user;
    private Tour tour;
    private double price;
    private int stepDisCount;
    private int disCount;
    private TourStatus tourStatus;
    private String description;
}
