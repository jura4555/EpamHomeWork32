package com.epam.spring.homework3.service;

import com.epam.spring.homework3.controller.dto.UserDTO;
import com.epam.spring.homework3.controller.dto.UserDetailsDTO;
import com.epam.spring.homework3.service.model.enums.UserRole;

import java.util.List;

public interface UserService {

    UserDTO getUserByLogin(String login);

    List<UserDTO> getUserByRole(UserRole userRole);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    UserDetailsDTO getUserDetails(int userId);
}
