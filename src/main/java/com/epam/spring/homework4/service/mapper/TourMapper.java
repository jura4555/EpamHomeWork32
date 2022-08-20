package com.epam.spring.homework4.service.mapper;

import com.epam.spring.homework4.controller.dto.TourDTO;
import com.epam.spring.homework4.service.model.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {HotelMapper.class})

public interface TourMapper {

    TourDTO mapTourToTourDTO(Tour tour);
    @Mapping(target = "burning", source = "burning", qualifiedByName="getBoolean")
    Tour mapTourDTOToTour(TourDTO tourDTO);

    @Named("getBoolean")
    default boolean getBoolean(Boolean burning) {
        return  (boolean) burning;
    }
}

