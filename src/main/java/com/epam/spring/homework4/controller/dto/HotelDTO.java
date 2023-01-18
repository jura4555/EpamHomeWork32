package com.epam.spring.homework4.controller.dto;

import com.epam.spring.homework4.service.model.enums.HotelType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class HotelDTO {

    private int id;

    @NotBlank(message = "{hotel.name.notBlank}")
    @Pattern(message = "{hotel.name.pattern}",
            regexp = "^[A-Za-z][A-Za-z\\d_-]{1,30}$")
    private String name;

    @NotBlank(message = "{hotel.city.notBlank}")
    @Pattern(message = "{hotel.city.pattern}",
            regexp = "^[A-Z][A-Za-z]{1,30}$")
    private String city;

    @NotNull(message = "{hotel.hotelType.notNull}")
    private HotelType hotelType;
}
