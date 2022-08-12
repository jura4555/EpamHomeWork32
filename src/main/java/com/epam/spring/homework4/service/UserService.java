package com.epam.spring.homework4.service;

import com.epam.spring.homework4.controller.dto.UserDTO;
import com.epam.spring.homework4.controller.dto.UserDetailsDTO;
import com.epam.spring.homework4.service.model.enums.UserRole;

import java.util.List;

public interface UserService {

    UserDTO getUserByLogin(String login);

    List<UserDTO> getUserByRole(UserRole userRole);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    UserDetailsDTO getUserDetails(int userId);
}
