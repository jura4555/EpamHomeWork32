package com.epam.spring.homework3.service.mapper;

import com.epam.spring.homework3.controller.dto.UserDetailsDTO;
import com.epam.spring.homework3.service.model.UserDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDetailsMapper {

    UserDetailsDTO mapUserDetailsToUserDetailsDto(UserDetails userDetails);

    UserDetails mapUserDetailsDtoToUserDetails(UserDetailsDTO userDetailsDTO);
}
