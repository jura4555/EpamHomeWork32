package com.epam.spring.travel_agency.service.mapper;

import com.epam.spring.travel_agency.controller.dto.HotelDTO;
import com.epam.spring.travel_agency.service.model.Hotel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelDTO mapHotelToHotelDto(Hotel hotel);

    Hotel mapHotelDtoToHotel(HotelDTO userDto);
}