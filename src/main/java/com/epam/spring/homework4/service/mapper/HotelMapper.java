package com.epam.spring.homework4.service.mapper;


import com.epam.spring.homework4.controller.dto.HotelDTO;
import com.epam.spring.homework4.service.model.Hotel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    HotelDTO mapHotelToHotelDto(Hotel hotel);

    Hotel mapHotelDtoToHotel(HotelDTO userDto);
}
