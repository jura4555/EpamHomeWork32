package com.epam.spring.homework4.controller.dto;

import com.epam.spring.homework4.controller.dto.validation.ValidBurning;
import com.epam.spring.homework4.controller.dto.validation.group.OnCreate;
import com.epam.spring.homework4.controller.dto.validation.group.OnUpdate;
import com.epam.spring.homework4.service.model.enums.TourType;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@Builder
public class TourDTO {

    private int id;

    @NotBlank(message = "tour.login.notBlank", groups = OnCreate.class)
    @Pattern(message = "tour.login.pattern",
            regexp = "^([A-Z])([A-Za-z]+\\w*){3,15}$")
    private String name;

    @Positive(message = "tour.price.positive")
    private double price;

    @FutureOrPresent(message = "tour.dateDaparture.futureOrPresent")
    private LocalDate dateDaparture;

    @FutureOrPresent(message = "tour.dateArrival.futureOrPresent")
    private LocalDate dateArrival;

    @NotBlank(message = "tour.placeDaparture.notBlank")
    private String placeDaparture;

    @NotBlank(message = "tour.placeArrival.notBlank")
    private String placeArrival;

    @Min(value = 0, message = "tour.maxDisCount.null", groups = OnCreate.class)
    @Max(value = 0, message = "tour.maxDisCount.null", groups = OnCreate.class)
    private int maxDisCount;

    @Positive(message = "tour.placeCount.positive", groups = OnCreate.class)
    @Min(value = 0, message = "tour.placeCount.null", groups = OnUpdate.class)
    @Max(value = 0, message = "tour.placeCount.null", groups = OnUpdate.class)
    private int placeCount;

    @Valid private HotelDTO hotel;

    @NotNull(message = "tour.tourType.notNull")
    private TourType tourType;

    @ValidBurning(message = "tour.burning.validBurning")
    private boolean burning;
}