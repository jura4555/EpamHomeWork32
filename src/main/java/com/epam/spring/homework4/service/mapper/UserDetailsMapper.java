package com.epam.spring.homework4.service.mapper;

import com.epam.spring.homework4.controller.dto.UserDetailsDTO;
import com.epam.spring.homework4.service.model.UserDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

    UserDetailsDTO mapUserDetailsToUserDetailsDto(UserDetails userDetails);

    UserDetails mapUserDetailsDtoToUserDetails(UserDetailsDTO userDetailsDTO);
}