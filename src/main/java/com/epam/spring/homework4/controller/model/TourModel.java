package com.epam.spring.homework4.controller.model;

import com.epam.spring.homework4.controller.dto.TourDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class TourModel extends RepresentationModel<TourModel> {

    @JsonUnwrapped
    private TourDTO tourDTO;
}
