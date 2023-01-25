package com.epam.spring.travel_agency.service;

import com.epam.spring.travel_agency.controller.dto.UserDTO;
import com.epam.spring.travel_agency.controller.dto.UserDetailsDTO;
import com.epam.spring.travel_agency.service.model.enums.UserRole;
import com.epam.spring.travel_agency.service.model.enums.UserStatus;

import java.util.List;

public interface UserService {

    UserDTO getUserByLogin(String login);

    List<UserDTO> getUserByRole(UserRole userRole);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(int id, UserDTO userDTO);

    UserDTO updateUserRole(int id, UserRole userRole);

    UserDTO updateUserStatus(int id, UserStatus userStatus);

    UserDetailsDTO getUserDetails(int id);
}
