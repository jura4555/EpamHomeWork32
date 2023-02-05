package com.epam.spring.travel_agency.service.repository;

import com.epam.spring.travel_agency.service.model.User;
import com.epam.spring.travel_agency.service.model.UserDetails;
import com.epam.spring.travel_agency.service.model.enums.UserRole;

import java.util.List;

public interface UserRepository {

    User getUserById(int id);

    User getUserByLogin(String login);

    List<User> getUserByRole(UserRole userRole);

    User createUser(User user);

    User updateUser(User user);

    UserDetails getUserDetails(int id);



}
