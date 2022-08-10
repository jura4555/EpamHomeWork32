package com.epam.spring.homework3.service.mapper;

import com.epam.spring.homework3.controller.dto.UserDTO;
import com.epam.spring.homework3.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {UserDetailsMapper.class})
public interface UserMapper {

    UserDTO mapUserToUserDTO(User user);
    @Mapping(target = "activated", source = "activated", qualifiedByName="getBoolean")
    User mapUserDTOToUser(UserDTO userDTO);

    @Named("getBoolean")
    default boolean getBoolean(Boolean activated) {
        return  (boolean) activated;
    }
}
