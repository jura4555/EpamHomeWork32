package com.epam.spring.travel_agency.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetails {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
}
