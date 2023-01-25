package com.epam.spring.travel_agency.service.model;

import com.epam.spring.travel_agency.service.model.enums.UserRole;
import com.epam.spring.travel_agency.service.model.enums.UserStatus;
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
    private UserStatus userStatus;


}
