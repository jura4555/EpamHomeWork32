package com.epam.spring.homework3.controller;

import com.epam.spring.homework3.controller.dto.UserDTO;
import com.epam.spring.homework3.controller.dto.UserDetailsDTO;
import com.epam.spring.homework3.service.UserService;
import com.epam.spring.homework3.service.model.enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
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
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        log.info("[Controller] createUser");
        return userService.createUser(userDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        log.info("[Controller] updateUser with all fields");
        return userService.updateUser(userDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{id}/user_details")
    public UserDetailsDTO getUserDetails(@PathVariable(value = "id") int userId){
        log.info("[Controller] getUserDetails by user id {} ", userId);
        return userService.getUserDetails(userId);
    }


}
