package com.epam.spring.homework4.controller;

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
@Validated
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/login/{login}")
    public UserDTO getUserByLogin(@PathVariable String login){
        log.info("[Controller] getUser by login {}", login);
        return userService.getUserByLogin(login);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/role/{userRole}")
    public List<UserDTO> getUserByRole(@PathVariable UserRole userRole) {
        log.info("[Controller] getUser by userRole {}", userRole);
        return userService.getUserByRole(userRole);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public UserDTO createUser(@RequestBody @Validated(OnCreate.class) UserDTO userDTO){
        log.info("[Controller] createUser");
        return userService.createUser(userDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping ("/user/{id}")
    public UserDTO updateUser(@PathVariable int id, @RequestBody @Validated(OnUpdate.class) UserDTO userDTO) {
        log.info("[Controller] updateUser with all fields");
        return userService.updateUser(id, userDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping ("/user/{id}/role")
    public UserDTO updateUserRole(@PathVariable int id, @RequestParam UserRole userRole) {
        log.info("[Controller] updateUser with userRole field");
        return userService.updateUserRole(id, userRole);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping ("/user/{id}/status")
    public UserDTO updateUserStatus(@PathVariable int id, @RequestParam UserStatus userStatus) {
        log.info("[Controller] updateUser with userRole field");
        return userService.updateUserStatus(id, userStatus);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{id}/user_details")
    public UserDetailsDTO getUserDetails(@PathVariable int id){
        log.info("[Controller] getUserDetails by user id {} ", id);
        return userService.getUserDetails(id);
    }

}
