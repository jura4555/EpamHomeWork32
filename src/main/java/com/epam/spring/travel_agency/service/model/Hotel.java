package com.epam.spring.travel_agency.service.model;

import com.epam.spring.travel_agency.service.model.converter.HotelTypeConverter;
import com.epam.spring.travel_agency.service.model.enums.HotelType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(name = "hotel_type_id", nullable = false)
    @Convert(converter = HotelTypeConverter.class)
    private HotelType hotelType;
}
