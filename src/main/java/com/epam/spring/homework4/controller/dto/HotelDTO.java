package com.epam.spring.homework4.controller.dto;

import com.epam.spring.homework4.service.model.enums.HotelType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelDTO {
    private int id;
    private String name;
    private String city;
    private HotelType hotelType;
}
