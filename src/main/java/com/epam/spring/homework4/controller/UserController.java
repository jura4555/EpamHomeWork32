package com.epam.spring.homework4.controller;

import com.epam.spring.homework4.controller.api.UserAPI;
import com.epam.spring.homework4.controller.dto.UserDTO;
import com.epam.spring.homework4.controller.dto.UserDetailsDTO;
import com.epam.spring.homework4.controller.dto.validation.group.OnCreate;
import com.epam.spring.homework4.controller.dto.validation.group.OnUpdate;
import com.epam.spring.homework4.service.UserService;
import com.epam.spring.homework4.service.model.enums.UserRole;
import com.epam.spring.homework4.service.model.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserAPI {

    private final UserService userService;

    @Override
    public UserDTO getUserByLogin(String login){
        log.info("[Controller] getUser by login {}", login);
        return userService.getUserByLogin(login);
    }

    @Override
    public List<UserDTO> getUserByRole(UserRole userRole) {
        log.info("[Controller] getUser by userRole {}", userRole);
        return userService.getUserByRole(userRole);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO){
        log.info("[Controller] createUser");
        return userService.createUser(userDTO);
    }

    @Override
    public UserDTO updateUser(int id, UserDTO userDTO) {
        log.info("[Controller] updateUser with all fields");
        return userService.updateUser(id, userDTO);
    }

    @Override
    public UserDTO updateUserRole(int id, UserRole userRole) {
        log.info("[Controller] updateUser with userRole field");
        return userService.updateUserRole(id, userRole);
    }

    @Override
    public UserDTO updateUserStatus(int id, UserStatus userStatus) {
        log.info("[Controller] updateUser with userRole field");
        return userService.updateUserStatus(id, userStatus);
    }

    @Override
    public UserDetailsDTO getUserDetails(int id){
        log.info("[Controller] getUserDetails by user id {} ", id);
        return userService.getUserDetails(id);
    }

}
