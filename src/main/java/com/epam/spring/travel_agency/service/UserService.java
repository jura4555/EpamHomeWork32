package com.epam.spring.travel_agency.service;

import com.epam.spring.travel_agency.controller.dto.UserDTO;
import com.epam.spring.travel_agency.controller.dto.UserDetailsDTO;
import com.epam.spring.travel_agency.service.model.enums.UserRole;
import com.epam.spring.travel_agency.service.model.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserDTO getUserByLogin(String login);

    Page<UserDTO> getUserByRole(UserRole userRole, int page, int size, String sortBy, String order);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(int id, UserDTO userDTO);

    UserDTO updateUserRole(int id, UserRole userRole);

    UserDTO updateUserStatus(int id, UserStatus userStatus);

    UserDetailsDTO getUserDetails(int id);
}
