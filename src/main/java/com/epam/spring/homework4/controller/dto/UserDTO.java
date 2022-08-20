package com.epam.spring.homework4.controller.dto;

import com.epam.spring.homework4.controller.dto.validation.group.OnCreate;
import com.epam.spring.homework4.controller.dto.validation.group.OnUpdate;
import com.epam.spring.homework4.service.model.enums.UserRole;
import com.epam.spring.homework4.service.model.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private int id;

    @NotBlank(message = "user.login.notBlank", groups = OnCreate.class)
    @Pattern(message = "user.login.pattern",
            regexp = "^([A-Za-z]+\\w*){3,15}$")
    private String login;

    @Pattern(message = "user.password.pattern",
            regexp = "^[A-Za-z][A-Za-z\\d_-]{5,15}$")
    @NotBlank(message = "user.password.notBlank", groups = OnCreate.class)
    private String password;

    @Pattern(message = "user.repeatPassword.pattern",
            regexp = "^[A-Za-z][A-Za-z\\d_-]{5,15}$")
    @NotBlank(message = "user.repeatPassword.notBlank", groups = OnCreate.class)
    private String repeatPassword;

    @Valid private UserDetailsDTO userDetails;

    @Null(message = "{user.role.null}", groups = OnCreate.class)
    private UserRole userRole;

    @Null(message = "{user.status.null}", groups = OnCreate.class)
    private UserStatus userStatus;
}