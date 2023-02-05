package com.epam.spring.travel_agency.controller.dto;

import com.epam.spring.travel_agency.controller.dto.validation.ValidDescription;
import com.epam.spring.travel_agency.controller.dto.validation.group.OnCreate;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private int id;

    private UserDTO user;

    private TourDTO tour;

    @Min(value = 0, message = "{order.price.null}", groups = OnCreate.class)
    @Max(value = 0, message = "{order.price.null}", groups = OnCreate.class)
    private double price;

    @Min(value = 0, message = "{order.stepDisCount.null}", groups = OnCreate.class)
    @Max(value = 0, message = "{order.stepDisCount.null}", groups = OnCreate.class)
    private int stepDisCount;

    @Min(value = 0, message = "{order.disCount.null}", groups = OnCreate.class)
    @Max(value = 0, message = "{order.disCount.null}", groups = OnCreate.class)
    private int disCount;

    @Null(message = "{order.tourStatus.null}", groups = OnCreate.class)
    private TourStatus tourStatus;

    @ValidDescription(message = "{order.description.validDescription}")
    private String description;
}
