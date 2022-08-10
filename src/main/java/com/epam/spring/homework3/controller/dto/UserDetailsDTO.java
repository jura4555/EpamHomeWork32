package com.epam.spring.homework3.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
}
