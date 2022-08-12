package com.epam.spring.homework4.service.model;

import com.epam.spring.homework4.service.model.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int id;
    private String login;
    private String password;
    private UserDetails userDetails;
    private UserRole userRole;
    private boolean activated;


}
