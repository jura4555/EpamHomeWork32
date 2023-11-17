package com.epam.spring.travel_agency.service.model;

import com.epam.spring.travel_agency.service.model.converter.LocalDateConverter;
import com.epam.spring.travel_agency.service.model.converter.TourTypeConverter;
import com.epam.spring.travel_agency.service.model.enums.TourType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(name = "date_departure", nullable = false)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dateDaparture;

    @Column(name = "date_arrival",nullable = false)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dateArrival;

    @Column(name = "place_departure", nullable = false)
    private String placeDaparture;

    @Column(name = "place_arrival", nullable = false)
    private String placeArrival;

    @Column(name = "max_discount")
    private int maxDisCount;

    @Column(name = "place_count", nullable = false)
    private int placeCount;

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @Column(name = "tour_type_id", nullable = false)
    @Convert(converter = TourTypeConverter.class)
    private TourType tourType;

    @Column(name = "is_burning", nullable = false)
    private boolean burning;
}
