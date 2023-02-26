package com.epam.spring.travel_agency.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {

    @NotBlank(message = "{userDetails.firstname.notBlank}")
    @Pattern(regexp = "[A-Z][a-z]{1,49}", message = "{userDetails.firstname.pattern}")
    private String firstname;

    @NotBlank(message = "{userDetails.lastname.notBlank}")
    @Pattern(regexp = "[A-Z][a-z]{1,49}", message = "{userDetails.lastname.pattern}")
    private String lastname;

    @NotBlank(message = "{userDetails.email.notBlank}")
    @Email(message = "{userDetails.email.email}")
    private String email;

    @NotNull(message = "{userDetails.phone.notNull}")
    @Pattern(regexp = "\\+380\\d{9}", message = "{userDetails.phone.pattern}")
    private String phone;
}
