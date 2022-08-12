package com.epam.spring.homework4.service.model;

import com.epam.spring.homework4.service.model.enums.HotelType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Hotel {
    private int id;
    private String name;
    private String city;
    private HotelType hotelType;
}
