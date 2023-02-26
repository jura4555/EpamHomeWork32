package com.epam.spring.travel_agency.controller;

import com.epam.spring.travel_agency.controller.api.UserAPI;
import com.epam.spring.travel_agency.controller.assembler.UserAssembler;
import com.epam.spring.travel_agency.controller.dto.UserDTO;
import com.epam.spring.travel_agency.controller.dto.UserDetailsDTO;
import com.epam.spring.travel_agency.controller.model.UserModel;
import com.epam.spring.travel_agency.service.UserService;
import com.epam.spring.travel_agency.service.model.enums.UserRole;
import com.epam.spring.travel_agency.service.model.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserAPI {

    private final UserService userService;
    private final UserAssembler userAssembler;

    @Override
    public UserModel getUserByLogin(String login){
        log.info("[Controller] getUser by login {}", login);
        UserDTO userDTO = userService.getUserByLogin(login);
        return userAssembler.toModel(userDTO);
    }

    @Override
    public Page<UserModel> getUserByRole(UserRole userRole, int page, int size, String sortBy, String order) {
        log.info("[Controller] getUser by userRole {}", userRole);
        Page<UserDTO> userDTOs = userService.getUserByRole(userRole, page, size, sortBy, order);
        Page<UserModel> userModels = userDTOs.map(userAssembler::toModel);
        return userModels;
    }

    @Override
    public UserModel createUser(UserDTO userDTO){
        log.info("[Controller] createUser");
        UserDTO createdUserDTO = userService.createUser(userDTO);
        return userAssembler.toModel(createdUserDTO);
    }

    @Override
    public UserModel updateUser(int id, UserDTO userDTO) {
        log.info("[Controller] updateUser with all fields");
        UserDTO updatedUserDTO = userService.updateUser(id, userDTO);
        return userAssembler.toModel(updatedUserDTO);
    }

    @Override
    public UserModel updateUserRole(int id, UserRole userRole) {
        log.info("[Controller] updateUser with userRole field");
        UserDTO userDTO = userService.updateUserRole(id, userRole);
        return userAssembler.toModel(userDTO);
    }

    @Override
    public UserModel updateUserStatus(int id, UserStatus userStatus) {
        log.info("[Controller] updateUser with userRole field");
        UserDTO userDTO = userService.updateUserStatus(id, userStatus);
        return userAssembler.toModel(userDTO);
    }

    @Override
    public UserDetailsDTO getUserDetails(int id){
        log.info("[Controller] getUserDetails by user id {} ", id);
        return userService.getUserDetails(id);
    }

}
