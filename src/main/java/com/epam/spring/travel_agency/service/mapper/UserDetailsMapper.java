package com.epam.spring.travel_agency.service.mapper;

import com.epam.spring.travel_agency.controller.dto.UserDetailsDTO;
import com.epam.spring.travel_agency.service.model.UserDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

    UserDetailsDTO mapUserDetailsToUserDetailsDto(UserDetails userDetails);

    UserDetails mapUserDetailsDtoToUserDetails(UserDetailsDTO userDetailsDTO);
}