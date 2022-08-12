package com.epam.spring.homework3.service.model.impl;

import com.epam.spring.homework3.service.exception.NotFoundException;
import com.epam.spring.homework3.service.model.User;
import com.epam.spring.homework3.service.model.UserDetails;
import com.epam.spring.homework3.service.model.enums.UserRole;
import com.epam.spring.homework3.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private int id;
    private final List<User> users = new ArrayList<>();

    @Override
    public User getUserByLogin(String login) {
        log.info("[Repository] getUser by login {} ", login);
        return users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("[Repository] User is not found!"));
    }

    @Override
    public List<User> getUserByRole(UserRole userRole) {
        log.info("[Repository] getUser by userRole {} ", userRole);
        List<User> myUsers = users.stream()
                .filter(u -> u.getUserRole().equals(userRole))
                .toList();
        if(myUsers.isEmpty()){
            throw new NotFoundException("[Repository] Users is not found!");
        }
        return myUsers;
    }

    @Override
    public User createUser(User user) {
        log.info("[Repository] createUser " + user.getLogin());
        id++;
        user.setId(id);
        user.getUserDetails().setId(id);
        users.add(user);
        log.info("[Repository] successfully created user with id:{}", user.getId());
        return user;
    }

    @Override
    public User updateUser(User user) {
        log.info("[Repository] updateUser by all field");
        int userId = user.getId();
        boolean isDeleted = users.removeIf(u -> u.getId() == userId);
        if(isDeleted) {
            users.add(user);
            log.info("[Repository] updateUser is done in user: " + user.getId());
        }
        else{
            throw new NotFoundException("[Repository] User is not found!");
        }
        return user;
    }

    @Override
    public UserDetails getUserDetails(int userId) {
        log.info("[Repository] getUserDetails by user id {} ", userId);
        User user = users.stream()
                .filter(u -> u.getId() == userId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("[Repository] User is not found!"));
        return user.getUserDetails();
    }

}
