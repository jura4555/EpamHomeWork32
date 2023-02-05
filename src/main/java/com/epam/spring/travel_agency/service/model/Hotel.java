package com.epam.spring.travel_agency.service.model;

import com.epam.spring.travel_agency.service.model.enums.HotelType;
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
