package com.epam.spring.homework3.service.mapper;


import com.epam.spring.homework3.controller.dto.HotelDTO;
import com.epam.spring.homework3.service.model.Hotel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelDTO mapHotelToHotelDto(Hotel hotel);

    Hotel mapHotelDtoToHotel(HotelDTO userDto);
}
