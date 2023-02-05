package com.epam.spring.travel_agency.controller.dto;

import com.epam.spring.travel_agency.service.model.enums.HotelType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
