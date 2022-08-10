package com.epam.spring.homework3.controller.dto;

import com.epam.spring.homework3.service.model.Hotel;
import com.epam.spring.homework3.service.model.enums.TourType;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourDTO {
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
