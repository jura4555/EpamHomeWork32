package com.epam.spring.travel_agency.service.model;

import com.epam.spring.travel_agency.service.model.enums.TourType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Tour {
    private int id;
    private String name;
    private double price;
    private LocalDate dateDaparture;
    private LocalDate dateArrival;
    private String placeDaparture;
    private String placeArrival;
    private int maxDisCount;
    private int placeCount;
    private Hotel hotel;
    private TourType tourType;
    private boolean burning;
}