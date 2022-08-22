package com.epam.spring.homework4.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class UserDetailsDTO {

    @NotBlank(message = "{UserDetails.firstname.notBlank}")
    @Pattern(regexp = "[A-Z][a-z]{1,49}", message = "{UserDetails.firstname.pattern}")
    private String firstname;

    @NotBlank(message = "{UserDetails.lastname.notBlank}")
    @Pattern(regexp = "[A-Z][a-z]{1,49}", message = "{UserDetails.lastname.pattern}")
    private String lastname;

    @NotBlank(message = "{UserDetails.email.notBlank}")
    @Email(message = "{UserDetails.email.email}")
    private String email;

    @NotNull(message = "{userDetails.phone.notNull}")
    @Pattern(regexp = "\\+380\\d{9}", message = "{userDetails.phone.pattern}")
    private String phone;
}
