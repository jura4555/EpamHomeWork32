package com.epam.spring.homework4.service.mapper;

import com.epam.spring.homework4.controller.dto.UserDTO;
import com.epam.spring.homework4.service.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserDetailsMapper.class})
public interface UserMapper {
    UserDTO mapUserToUserDTO(User user);
    User mapUserDTOToUser(UserDTO userDTO);


}
