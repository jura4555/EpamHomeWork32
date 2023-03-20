package com.epam.spring.travel_agency.service;

import com.epam.spring.travel_agency.controller.dto.UserDTO;
import com.epam.spring.travel_agency.controller.dto.UserDetailsDTO;
import com.epam.spring.travel_agency.service.exception.*;
import com.epam.spring.travel_agency.service.impl.UserServiceImpl;
import com.epam.spring.travel_agency.service.mapper.UserDetailsMapper;
import com.epam.spring.travel_agency.service.mapper.UserMapper;
import com.epam.spring.travel_agency.service.model.User;
import com.epam.spring.travel_agency.service.model.UserDetails;
import com.epam.spring.travel_agency.service.model.enums.UserRole;
import com.epam.spring.travel_agency.service.model.enums.UserStatus;
import com.epam.spring.travel_agency.service.repository.UserDetailsRepository;
import com.epam.spring.travel_agency.service.repository.UserRepository;
import com.epam.spring.travel_agency.test.util.TestUserDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserDetailsMapper userDetailsMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUserTest(){
        User user = TestUserDataUtil.getUser1();
        UserDTO userDTO = TestUserDataUtil.getUserDTO1();
        when(userRepository.findByLogin(user.getLogin())).thenReturn(Optional.of(user));
        when(userMapper.mapUserToUserDTO(user)).thenReturn(userDTO);
        UserDTO result = userService.getUserByLogin(user.getLogin());
        assertThat(result, allOf(
                hasProperty("id", equalTo(userDTO.getId())),
                hasProperty("login", equalTo(userDTO.getLogin())),
                hasProperty("password", equalTo(userDTO.getPassword())),
                hasProperty("userRole", equalTo(userDTO.getUserRole())),
                hasProperty("userStatus", equalTo(userDTO.getUserStatus()))
        ));
        assertThat(result.getUserDetails(), allOf(
                hasProperty("firstname", equalTo(userDTO.getUserDetails().getFirstname())),
                hasProperty("lastname", equalTo(userDTO.getUserDetails().getLastname())),
                hasProperty("email", equalTo(userDTO.getUserDetails().getEmail())),
                hasProperty("phone", equalTo(userDTO.getUserDetails().getPhone()))
        ));
    }

    @Test
    void getUserWithNotFoundExceptionTest(){
        when(userRepository.findByLogin(anyString())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserByLogin(anyString()));
    }

    @Test
    void getUsersTest(){
        User user1 = TestUserDataUtil.getUser1();
        User user2 = TestUserDataUtil.getUser2();
        UserDTO userDTO1 = TestUserDataUtil.getUserDTO1();
        UserDTO userDTO2 = TestUserDataUtil.getUserDTO2();
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<User> users = new PageImpl<>(List.of(user1, user2), pageable, 2);
        when(userRepository.findByUserRole(UserRole.USER, pageable)).thenReturn(users);
        when(userMapper.mapUserToUserDTO(user1)).thenReturn(userDTO1);
        when(userMapper.mapUserToUserDTO(user2)).thenReturn(userDTO2);
        Page<UserDTO> result = userService.getUserByRole(UserRole.USER, 1,2, "id", "asc");
        assertThat(result.getTotalElements(), is(users.getTotalElements()));
        assertThat(result, contains(userDTO1, userDTO2));
    }

    @Test
    void createUserTest(){
        User user = TestUserDataUtil.getUser1();
        UserDTO userDTO = TestUserDataUtil.getUserDTOForCreate();
        when(userMapper.mapUserDTOToUser(userDTO)).thenReturn(TestUserDataUtil.getUserForCreate());
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.mapUserToUserDTO(user)).thenReturn(TestUserDataUtil.getUserDTO1());
        UserDTO result = userService.createUser(userDTO);
        assertThat(result, allOf(
                hasProperty("id", equalTo(userDTO.getId())),
                hasProperty("login", equalTo(userDTO.getLogin())),
                hasProperty("password", equalTo(userDTO.getPassword())),
                hasProperty("userRole", equalTo(UserRole.USER)),
                hasProperty("userStatus", equalTo(UserStatus.ACTIVE))
        ));
        assertThat(result.getUserDetails(), allOf(
                hasProperty("firstname", equalTo(userDTO.getUserDetails().getFirstname())),
                hasProperty("lastname", equalTo(userDTO.getUserDetails().getLastname())),
                hasProperty("email", equalTo(userDTO.getUserDetails().getEmail())),
                hasProperty("phone", equalTo(userDTO.getUserDetails().getPhone()))
        ));
    }

    @Test
    void createUserWithUserLoginAlreadyExistsExceptionTest(){
        UserDTO userDTO = TestUserDataUtil.getUserDTOForCreate();
        when(userRepository.existsByLogin(userDTO.getLogin())).thenReturn(true);
        assertThrows(UserLoginAlreadyExistsException.class, () -> userService.createUser(userDTO));
    }

    @Test
    void createUserWithUserRepeatPasswordExceptionTest(){
        UserDTO userDTO = TestUserDataUtil.getUserDTOForCreate();
        userDTO.setRepeatPassword("other");
        assertThrows(UserRepeatPasswordException.class, () -> userService.createUser(userDTO));
    }


    @Test
    void createUserWithUserEmailAlreadyExistsExceptionTest(){
        UserDTO userDTO = TestUserDataUtil.getUserDTOForCreate();
        when(userDetailsRepository.existsByEmail(userDTO.getUserDetails().getEmail())).thenReturn(true);
        assertThrows(UserEmailAlreadyExistsException.class, () -> userService.createUser(userDTO));
    }

    @Test
    void createUserWithUserPhoneAlreadyExistsExceptionTest(){
        UserDTO userDTO = TestUserDataUtil.getUserDTOForCreate();
        when(userDetailsRepository.existsByPhone(userDTO.getUserDetails().getPhone())).thenReturn(true);
        assertThrows(UserPhoneAlreadyExistsException.class, () -> userService.createUser(userDTO));
    }

    @Test
    void updateUserTest(){
        User user = TestUserDataUtil.getUser1();
        UserDTO dbUserDTO = TestUserDataUtil.getUserDTOForUpdate();
        User dbUser = TestUserDataUtil.getUserForUpdate();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userMapper.mapUserDTOToUser(dbUserDTO)).thenReturn(dbUser);
        when(userRepository.save(dbUser)).thenReturn(dbUser);
        when(userMapper.mapUserToUserDTO(dbUser)).thenReturn(TestUserDataUtil.getUserDTOAfterUpdate());
        UserDTO result = userService.updateUser(user.getId(),dbUserDTO);
        assertThat(result, allOf(
                hasProperty("id", equalTo(user.getId())),
                hasProperty("login", equalTo(dbUserDTO.getLogin())),
                hasProperty("password", equalTo(dbUserDTO.getPassword())),
                hasProperty("userRole", equalTo(user.getUserRole())),
                hasProperty("userStatus", equalTo(user.getUserStatus()))
        ));
        assertThat(result.getUserDetails(), allOf(
                hasProperty("firstname", equalTo(dbUserDTO.getUserDetails().getFirstname())),
                hasProperty("lastname", equalTo(dbUserDTO.getUserDetails().getLastname())),
                hasProperty("email", equalTo(dbUserDTO.getUserDetails().getEmail())),
                hasProperty("phone", equalTo(dbUserDTO.getUserDetails().getPhone()))
        ));
    }

    @Test
    void updateUserWithUserNotFoundExceptionTest(){
        int inputId = 10;
        UserDTO userDTO = TestUserDataUtil.getUserDTOForUpdate();
        when(userRepository.findById(inputId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(inputId, userDTO));
    }

    @Test
    void updateUserWithUserRepeatPasswordExceptionTest(){
        int inputId = 1;
        UserDTO userDTO = TestUserDataUtil.getUserDTOForUpdate();
        userDTO.setRepeatPassword("other");
        when(userRepository.findById(inputId)).thenReturn(Optional.of(TestUserDataUtil.getUser1()));
        assertThrows(UserRepeatPasswordException.class, () -> userService.updateUser(inputId, userDTO));
    }

    @Test
    void updateUserWithUserEmailAlreadyExistsExceptionTest(){
        int inputId = 1;
        UserDTO userDTO = TestUserDataUtil.getUserDTOForUpdate();
        when(userRepository.findById(inputId)).thenReturn(Optional.of(TestUserDataUtil.getUser1()));
        when(userDetailsRepository.existsByEmail(userDTO.getUserDetails().getEmail())).thenReturn(true);
        assertThrows(UserEmailAlreadyExistsException.class, () -> userService.updateUser(inputId, userDTO));
    }

    @Test
    void updateUserWithUserPhoneAlreadyExistsExceptionTest(){
        int inputId = 1;
        UserDTO userDTO = TestUserDataUtil.getUserDTOForUpdate();
        when(userRepository.findById(inputId)).thenReturn(Optional.of(TestUserDataUtil.getUser1()));
        when(userDetailsRepository.existsByPhone(userDTO.getUserDetails().getPhone())).thenReturn(true);
        assertThrows(UserPhoneAlreadyExistsException.class, () -> userService.updateUser(inputId, userDTO));
    }

    @Test
    void updateUserRoleTest(){
        UserRole userRole = UserRole.MANAGER;
        User user = TestUserDataUtil.getUser1();
        UserDTO userDTO = TestUserDataUtil.getUserDTO1();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        user.setUserRole(userRole);
        userDTO.setUserRole(userRole);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.mapUserToUserDTO(user)).thenReturn(userDTO);
        UserDTO result = userService.updateUserRole(user.getId(), userRole);
        assertThat(result, allOf(
                hasProperty("id", equalTo(userDTO.getId())),
                hasProperty("login", equalTo(userDTO.getLogin())),
                hasProperty("password", equalTo(userDTO.getPassword())),
                hasProperty("userRole", equalTo(userDTO.getUserRole())),
                hasProperty("userStatus", equalTo(userDTO.getUserStatus()))
        ));
        assertThat(result.getUserDetails(), allOf(
                hasProperty("firstname", equalTo(userDTO.getUserDetails().getFirstname())),
                hasProperty("lastname", equalTo(userDTO.getUserDetails().getLastname())),
                hasProperty("email", equalTo(userDTO.getUserDetails().getEmail())),
                hasProperty("phone", equalTo(userDTO.getUserDetails().getPhone()))
        ));
    }

    @Test
    void updateUserRoleWithUserNotFoundExceptionTest(){
        int inputId = 10;
        when(userRepository.findById(inputId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.updateUserRole(inputId, UserRole.MANAGER));
    }

    @Test
    void updateUserStatusTest(){
        UserStatus userStatus = UserStatus.BLOCKED;
        User user = TestUserDataUtil.getUser1();
        UserDTO userDTO = TestUserDataUtil.getUserDTO1();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        user.setUserStatus(userStatus);
        userDTO.setUserStatus(userStatus);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.mapUserToUserDTO(user)).thenReturn(userDTO);
        UserDTO result = userService.updateUserStatus(user.getId(), userStatus);
        assertThat(result, allOf(
                hasProperty("id", equalTo(userDTO.getId())),
                hasProperty("login", equalTo(userDTO.getLogin())),
                hasProperty("password", equalTo(userDTO.getPassword())),
                hasProperty("userRole", equalTo(userDTO.getUserRole())),
                hasProperty("userStatus", equalTo(userDTO.getUserStatus()))
        ));
        assertThat(result.getUserDetails(), allOf(
                hasProperty("firstname", equalTo(userDTO.getUserDetails().getFirstname())),
                hasProperty("lastname", equalTo(userDTO.getUserDetails().getLastname())),
                hasProperty("email", equalTo(userDTO.getUserDetails().getEmail())),
                hasProperty("phone", equalTo(userDTO.getUserDetails().getPhone()))
        ));
    }

    @Test
    void updateUserStatusWithUserNotFoundExceptionTest(){
        int inputId = 10;
        when(userRepository.findById(inputId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.updateUserStatus(inputId, UserStatus.BLOCKED));
    }

    @Test
    void getUserDetailsTest(){
        User user = TestUserDataUtil.getUser1();
        UserDTO userDTO = TestUserDataUtil.getUserDTO1();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userDetailsMapper.mapUserDetailsToUserDetailsDto(user.getUserDetails())).thenReturn(userDTO.getUserDetails());
        UserDetailsDTO result = userService.getUserDetails(user.getId());
        assertThat(result, allOf(
                hasProperty("firstname", equalTo(userDTO.getUserDetails().getFirstname())),
                hasProperty("lastname", equalTo(userDTO.getUserDetails().getLastname())),
                hasProperty("email", equalTo(userDTO.getUserDetails().getEmail())),
                hasProperty("phone", equalTo(userDTO.getUserDetails().getPhone()))
        ));
    }

    @Test
    void getUserDetailsWithUserNotFoundExceptionTest(){
        int inputId = 10;
        when(userRepository.findById(inputId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserDetails(inputId));
    }

}
