package com.epam.spring.travel_agency.controller.model;

import com.epam.spring.travel_agency.controller.dto.HotelDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class HotelModel extends RepresentationModel<TourModel> {

    @JsonUnwrapped
    private HotelDTO hotelDTO;
}
