package com.epam.spring.travel_agency.controller;

import com.epam.spring.travel_agency.controller.assembler.UserAssembler;
import com.epam.spring.travel_agency.controller.dto.UserDTO;
import com.epam.spring.travel_agency.service.UserService;
import com.epam.spring.travel_agency.service.model.enums.UserRole;
import com.epam.spring.travel_agency.service.model.enums.UserStatus;
import com.epam.spring.travel_agency.test.config.TestConfig;
import com.epam.spring.travel_agency.test.util.TestUserDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.epam.spring.travel_agency.test.util.TestUserDataUtil.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(TestConfig.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @SpyBean
    private UserAssembler userAssembler;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUserByLoginTest() throws Exception{
        UserDTO user = TestUserDataUtil.getUserDTO1();
        when(userService.getUserByLogin(USER_1_LOGIN)).thenReturn(user);
        mockMvc.perform(get("/api/v1/user/login/{login}", USER_1_LOGIN ))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(user.getId()),
                        jsonPath("$.login").value(user.getLogin()),
                        jsonPath("$.password").value(user.getPassword()),
                        jsonPath("$.userRole").value(user.getUserRole().name()),
                        jsonPath("$.userStatus").value(user.getUserStatus().name()),
                        jsonPath("$.userDetails.firstname").value(user.getUserDetails().getFirstname()),
                        jsonPath("$.userDetails.lastname").value(user.getUserDetails().getLastname()),
                        jsonPath("$.userDetails.email").value(user.getUserDetails().getEmail()),
                        jsonPath("$.userDetails.phone").value(user.getUserDetails().getPhone()),
                        jsonPath("$.links").value(hasSize(5))
                );
        verify(userService, times(1)).getUserByLogin(USER_1_LOGIN);
        verify(userAssembler, times(1)).toModel(user);
    }

    @Test
    void getUserByRoleTest() throws Exception{
        UserDTO user1 = TestUserDataUtil.getUserDTO1();
        UserDTO user2 = TestUserDataUtil.getUserDTO2();
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<UserDTO> users = new PageImpl<>(List.of(user1, user2), pageable, 2);
        when(userService.getUserByRole(UserRole.USER,1, 2, "id", "asc")).thenReturn(users);
        mockMvc.perform(get("/api/v1/user/role/{userRole}", UserRole.USER)
                        .queryParam("page", String.valueOf(1))
                        .queryParam("size", String.valueOf(2))
                        .queryParam("sortBy", "id")
                        .queryParam("order", "asc"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.pageable.pageNumber").value(0),
                        jsonPath("$.pageable.pageSize").value(2),
                        jsonPath("$.content[0].id").value(user1.getId()),
                        jsonPath("$.content[0].links").value(hasSize(5)),
                        jsonPath("$.content[1].id").value(user2.getId()),
                        jsonPath("$.content[1].links").value(hasSize(5))
                );
        verify(userService, times(1)).getUserByRole(UserRole.USER, 1, 2, "id", "asc");
        verify(userAssembler, times(1)).toModel(user1);
        verify(userAssembler, times(1)).toModel(user2);
    }

    @Test
    void createUserTest() throws Exception{
        ObjectMapper jsonMapper = new ObjectMapper();
        UserDTO createdUser = TestUserDataUtil.getUserDTOForCreate();
        UserDTO user = TestUserDataUtil.getUserDTO1();
        when(userService.createUser(createdUser)).thenReturn(user);
        mockMvc.perform(post("/api/v1/user/")
                .contentType("application/json")
                .content(jsonMapper.writeValueAsString(createdUser)))
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(user.getId()),
                        jsonPath("$.login").value(user.getLogin()),
                        jsonPath("$.password").value(user.getPassword()),
                        jsonPath("$.userRole").value(user.getUserRole().name()),
                        jsonPath("$.userStatus").value(user.getUserStatus().name()),
                        jsonPath("$.userDetails.firstname").value(user.getUserDetails().getFirstname()),
                        jsonPath("$.userDetails.lastname").value(user.getUserDetails().getLastname()),
                        jsonPath("$.userDetails.email").value(user.getUserDetails().getEmail()),
                        jsonPath("$.userDetails.phone").value(user.getUserDetails().getPhone()),
                        jsonPath("$.links").value(hasSize(5))
                );
        verify(userService, times(1)).createUser(createdUser);
        verify(userAssembler, times(1)).toModel(user);
    }

    @Test
    void updateUserTest() throws Exception{
        ObjectMapper jsonMapper = new ObjectMapper();
        UserDTO user = TestUserDataUtil.getUserDTOForUpdate();
        when(userService.updateUser(USER_1_ID, user)).thenReturn(TestUserDataUtil.getUserDTOAfterUpdate());
        mockMvc.perform(put("/api/v1/user/{id}", USER_1_ID)
                        .contentType("application/json")
                        .content(jsonMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(USER_1_ID),
                        jsonPath("$.login").value(user.getLogin()),
                        jsonPath("$.password").value(user.getPassword()),
                        jsonPath("$.userRole").value(USER_1_ROLE.name()),
                        jsonPath("$.userStatus").value(USER_1_STATUS.name()),
                        jsonPath("$.userDetails.firstname").value(user.getUserDetails().getFirstname()),
                        jsonPath("$.userDetails.lastname").value(user.getUserDetails().getLastname()),
                        jsonPath("$.userDetails.email").value(user.getUserDetails().getEmail()),
                        jsonPath("$.userDetails.phone").value(user.getUserDetails().getPhone()),
                        jsonPath("$.links").value(hasSize(5))
                );
        verify(userService, times(1)).updateUser(USER_1_ID, user);
        verify(userAssembler, times(1)).toModel(TestUserDataUtil.getUserDTOAfterUpdate());
    }

    @Test
    void updateUserRoleTest() throws Exception{
        UserRole newUserRole = UserRole.MANAGER;
        UserDTO user = TestUserDataUtil.getUserDTO1();
        user.setUserRole(newUserRole);
        when(userService.updateUserRole(user.getId(), newUserRole)).thenReturn(user);
        mockMvc.perform(patch("/api/v1/user/{id}/role", user.getId())
                        .queryParam("userRole", newUserRole.toString())
                )
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(user.getId()),
                        jsonPath("$.login").value(user.getLogin()),
                        jsonPath("$.password").value(user.getPassword()),
                        jsonPath("$.userRole").value(user.getUserRole().name()),
                        jsonPath("$.userStatus").value(user.getUserStatus().name()),
                        jsonPath("$.userDetails.firstname").value(user.getUserDetails().getFirstname()),
                        jsonPath("$.userDetails.lastname").value(user.getUserDetails().getLastname()),
                        jsonPath("$.userDetails.email").value(user.getUserDetails().getEmail()),
                        jsonPath("$.userDetails.phone").value(user.getUserDetails().getPhone()),
                        jsonPath("$.links").value(hasSize(5))
                );
        verify(userService, times(1)).updateUserRole(user.getId(), newUserRole);
        verify(userAssembler, times(1)).toModel(user);
    }

    @Test
    void updateUserStatusTest() throws Exception{
        UserStatus newUserStatus = UserStatus.BLOCKED;
        UserDTO user = TestUserDataUtil.getUserDTO1();
        user.setUserStatus(newUserStatus);
        when(userService.updateUserStatus(user.getId(), newUserStatus)).thenReturn(user);
        mockMvc.perform(patch("/api/v1/user/{id}/status", user.getId())
                        .queryParam("userStatus", newUserStatus.toString())
                )
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(user.getId()),
                        jsonPath("$.login").value(user.getLogin()),
                        jsonPath("$.password").value(user.getPassword()),
                        jsonPath("$.userRole").value(user.getUserRole().name()),
                        jsonPath("$.userStatus").value(user.getUserStatus().name()),
                        jsonPath("$.userDetails.firstname").value(user.getUserDetails().getFirstname()),
                        jsonPath("$.userDetails.lastname").value(user.getUserDetails().getLastname()),
                        jsonPath("$.userDetails.email").value(user.getUserDetails().getEmail()),
                        jsonPath("$.userDetails.phone").value(user.getUserDetails().getPhone()),
                        jsonPath("$.links").value(hasSize(5))
                );
        verify(userService, times(1)).updateUserStatus(user.getId(), newUserStatus);
        verify(userAssembler, times(1)).toModel(user);
    }

    @Test
    void UserDetailsTest() throws Exception{
        UserDTO user = TestUserDataUtil.getUserDTO1();
        when(userService.getUserDetails(user.getId())).thenReturn(user.getUserDetails());
        mockMvc.perform(get("/api/v1/user/{id}/user_details", user.getId()))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.firstname").value(user.getUserDetails().getFirstname()),
                        jsonPath("$.lastname").value(user.getUserDetails().getLastname()),
                        jsonPath("$.email").value(user.getUserDetails().getEmail()),
                        jsonPath("$.phone").value(user.getUserDetails().getPhone())
                );
        verify(userService, times(1)).getUserDetails(user.getId());
    }

}
