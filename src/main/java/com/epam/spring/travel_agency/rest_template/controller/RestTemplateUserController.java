package com.epam.spring.travel_agency.rest_template.controller;

import com.epam.spring.travel_agency.controller.dto.UserDTO;
import com.epam.spring.travel_agency.controller.dto.UserDetailsDTO;
import com.epam.spring.travel_agency.rest_template.service.RestTemplateUserService;
import com.epam.spring.travel_agency.service.model.enums.UserRole;
import com.epam.spring.travel_agency.service.model.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RestTemplate")
@RequiredArgsConstructor
public class RestTemplateUserController {

    private final RestTemplateUserService restTemplateUserService;

    @GetMapping("/user/login/{login}")
    public ResponseEntity<UserDTO> getUserByLogin(@PathVariable String login){
        return  restTemplateUserService.getUserByLogin(login);
    }

    @GetMapping("/user/role/{userRole}")
    public ResponseEntity<List<UserDTO>> getUserByRole(@PathVariable UserRole userRole){
        return restTemplateUserService.getUserByRole(userRole);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        return restTemplateUserService.createUser(userDTO);
    }

    @PutMapping ("/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO){
        return restTemplateUserService.updateUser(id, userDTO);
    }

    @PatchMapping ("/user/{id}/role")
    public ResponseEntity<UserDTO> updateUserRole(@PathVariable int id, @RequestParam UserRole userRole) {
        return restTemplateUserService.updateUserRole(id, userRole);
    }

    @PatchMapping ("/user/{id}/status")
    public ResponseEntity<UserDTO> updateUserStatus(@PathVariable int id, @RequestParam UserStatus userStatus){
        return restTemplateUserService.updateUserStatus(id, userStatus);
    }

    @GetMapping("/user/{id}/user_details")
    public ResponseEntity<UserDetailsDTO> getUserDetails(@PathVariable int id){
        return restTemplateUserService.getUserDetails(id);
    }

}
