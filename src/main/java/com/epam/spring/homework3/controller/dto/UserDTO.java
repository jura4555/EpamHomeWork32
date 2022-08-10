package com.epam.spring.homework3.controller.dto;

import com.epam.spring.homework3.service.model.UserDetails;
import com.epam.spring.homework3.service.model.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private int id;
    private String login;
    private String password;
    private String repeatPassword;
    private UserDetails userDetails;
    private UserRole userRole;
    private boolean activated;
}
