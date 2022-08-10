package com.epam.spring.homework3.service.repository;

import com.epam.spring.homework3.service.model.User;
import com.epam.spring.homework3.service.model.UserDetails;
import com.epam.spring.homework3.service.model.enums.UserRole;

import java.util.List;

public interface UserRepository {

    User getUserByLogin(String login);

    List<User> getUserByRole(UserRole userRole);

    User createUser(User user);

    User updateUser(User user);

    UserDetails getUserDetails(int userId);



}
