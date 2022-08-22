package com.epam.spring.homework4.service.repository.impl;

import com.epam.spring.homework4.service.exception.UserNotFoundException;
import com.epam.spring.homework4.service.model.User;
import com.epam.spring.homework4.service.model.UserDetails;
import com.epam.spring.homework4.service.model.enums.UserRole;
import com.epam.spring.homework4.service.model.enums.UserStatus;
import com.epam.spring.homework4.service.repository.UserRepository;
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

    public User getUserById(int id) {
        log.info("[Repository] getUser by id {} ", id);
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException());
    }
    @Override
    public User getUserByLogin(String login) {
        log.info("[Repository] getUser by login {} ", login);
        return users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public List<User> getUserByRole(UserRole userRole) {
        log.info("[Repository] getUser by userRole {} ", userRole);
        List<User> myUsers = users.stream()
                .filter(u -> u.getUserRole().equals(userRole))
                .toList();
        if(myUsers.isEmpty()){
            throw new UserNotFoundException();
        }
        return myUsers;
    }

    @Override
    public User createUser(User user) {
        log.info("[Repository] createUser " + user.getId());
        id++;
        user.setId(id);
        user.getUserDetails().setId(id);
        user.setUserRole(UserRole.USER);
        user.setUserStatus(UserStatus.ACTIVE);
        users.add(user);
        log.info("[Repository] successfully created user with id:{}", user.getId());
        return user;
    }

    @Override
    public User updateUser(User user) {
        log.info("[Repository] updateUser by all field");
        users.removeIf(u -> u.getLogin().equals(user.getLogin()));
        users.add(user);
        log.info("[Repository] updateUser is done in user id: " + user.getId());
        return user;
    }

    @Override
    public UserDetails getUserDetails(int id) {
        log.info("[Repository] getUserDetails by user id {} ", id);
        User user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException());
        return user.getUserDetails();
    }

}
