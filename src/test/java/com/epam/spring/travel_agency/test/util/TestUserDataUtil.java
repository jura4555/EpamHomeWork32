package com.epam.spring.travel_agency.test.util;

import com.epam.spring.travel_agency.controller.dto.UserDTO;
import com.epam.spring.travel_agency.controller.dto.UserDetailsDTO;
import com.epam.spring.travel_agency.service.model.User;
import com.epam.spring.travel_agency.service.model.UserDetails;
import com.epam.spring.travel_agency.service.model.enums.UserRole;
import com.epam.spring.travel_agency.service.model.enums.UserStatus;

public class TestUserDataUtil {

    public static final int USER_1_ID = 1;
    public static final String USER_1_LOGIN = "user1";
    public static final String USER_1_PASSWORD = "password-1";
    public static final String USER_1_REPEAT_PASSWORD = "password-1";
    public static final UserRole USER_1_ROLE = UserRole.USER;
    public static final UserStatus USER_1_STATUS = UserStatus.ACTIVE;
    public static final int USER_DETAILS_1_ID = 1;
    public static final String USER_DETAILS_1_FIRST_NAME = "Yurii";
    public static final String USER_DETAILS_1_LAST_NAME = "Fedynets";
    public static final String USER_DETAILS_1_EMAIL = "jurii_fedynets@gmail.com";
    public static final String USER_DETAILS_1_PHONE = "+380684864182";

    public static final int USER_2_ID = 2;
    public static final String USER_2_LOGIN = "user2";
    public static final String USER_2_PASSWORD = "password-2";
    public static final String USER_2_REPEAT_PASSWORD = "password-2";
    public static final UserRole USER_2_ROLE = UserRole.USER;
    public static final UserStatus USER_2_STATUS = UserStatus.ACTIVE;
    public static final int USER_DETAILS_2_ID = 2;
    public static final String USER_DETAILS_2_FIRST_NAME = "Vasyl";
    public static final String USER_DETAILS_2_LAST_NAME = "Melnyk";
    public static final String USER_DETAILS_2_EMAIL = "vasyl_melnyk@gmail.com";
    public static final String USER_DETAILS_2_PHONE = "+380674664482";

    public static final String USER_UPDATE_LOGIN = "user3";
    public static final String USER_UPDATE_PASSWORD = "password-3";
    public static final String USER_UPDATE_REPEAT_PASSWORD = "password-3";
    public static final String USER_DETAILS_UPDATE_FIRST_NAME = "Oleh";
    public static final String USER_DETAILS_UPDATE_LAST_NAME = "Melnyk";
    public static final String USER_DETAILS_UPDATE_EMAIL = "oleh_melnyk@gmail.com";
    public static final String USER_DETAILS_UPDATE_PHONE = "+380674664472";

    public static User getUser1(){
        return User.builder()
                .id(USER_1_ID)
                .login(USER_1_LOGIN)
                .password(USER_1_PASSWORD)
                .userDetails(
                        UserDetails.builder()
                                .id(USER_DETAILS_1_ID)
                                .firstname(USER_DETAILS_1_FIRST_NAME)
                                .lastname(USER_DETAILS_1_LAST_NAME)
                                .email(USER_DETAILS_1_EMAIL)
                                .phone(USER_DETAILS_1_PHONE)
                                .build())
                .userRole(USER_1_ROLE)
                .userStatus(USER_1_STATUS)
                .build();
    }

    public static User getUser2(){
        return User.builder()
                .id(USER_2_ID)
                .login(USER_2_LOGIN)
                .password(USER_2_PASSWORD)
                .userDetails(
                        UserDetails.builder()
                                .id(USER_DETAILS_2_ID)
                                .firstname(USER_DETAILS_2_FIRST_NAME)
                                .lastname(USER_DETAILS_2_LAST_NAME)
                                .email(USER_DETAILS_2_EMAIL)
                                .phone(USER_DETAILS_2_PHONE)
                                .build())
                .userRole(USER_2_ROLE)
                .userStatus(USER_2_STATUS)
                .build();
    }


    public static UserDTO getUserDTO1(){
        return UserDTO.builder()
                .id(USER_1_ID)
                .login(USER_1_LOGIN)
                .password(USER_1_PASSWORD)
                .repeatPassword(USER_1_REPEAT_PASSWORD)
                .userDetails(
                        UserDetailsDTO.builder()
                                .firstname(USER_DETAILS_1_FIRST_NAME)
                                .lastname(USER_DETAILS_1_LAST_NAME)
                                .email(USER_DETAILS_1_EMAIL)
                                .phone(USER_DETAILS_1_PHONE)
                                .build())
                .userRole(USER_1_ROLE)
                .userStatus(USER_1_STATUS)
                .build();
    }

    public static UserDTO getUserDTO2(){
        return UserDTO.builder()
                .id(USER_2_ID)
                .login(USER_2_LOGIN)
                .password(USER_2_PASSWORD)
                .repeatPassword(USER_2_REPEAT_PASSWORD)
                .userDetails(
                        UserDetailsDTO.builder()
                                .firstname(USER_DETAILS_2_FIRST_NAME)
                                .lastname(USER_DETAILS_2_LAST_NAME)
                                .email(USER_DETAILS_2_EMAIL)
                                .phone(USER_DETAILS_2_PHONE)
                                .build())
                .userRole(USER_2_ROLE)
                .userStatus(USER_2_STATUS)
                .build();
    }

    public static User getUserForCreate(){
        return User.builder()
                .login(USER_1_LOGIN)
                .password(USER_1_PASSWORD)
                .userDetails(
                        UserDetails.builder()
                                .firstname(USER_DETAILS_1_FIRST_NAME)
                                .lastname(USER_DETAILS_1_LAST_NAME)
                                .email(USER_DETAILS_1_EMAIL)
                                .phone(USER_DETAILS_1_PHONE)
                                .build())
                .build();
    }


    public static UserDTO getUserDTOForCreate(){
        return UserDTO.builder()
                .login(USER_1_LOGIN)
                .password(USER_1_PASSWORD)
                .repeatPassword(USER_1_REPEAT_PASSWORD)
                .userDetails(
                        UserDetailsDTO.builder()
                                .firstname(USER_DETAILS_1_FIRST_NAME)
                                .lastname(USER_DETAILS_1_LAST_NAME)
                                .email(USER_DETAILS_1_EMAIL)
                                .phone(USER_DETAILS_1_PHONE)
                                .build())
                .build();
    }

    public static User getUserForUpdate(){
        return User.builder()
                .login(USER_UPDATE_LOGIN)
                .password(USER_UPDATE_PASSWORD)
                .userDetails(
                        UserDetails.builder()
                                .firstname(USER_DETAILS_UPDATE_FIRST_NAME)
                                .lastname(USER_DETAILS_UPDATE_LAST_NAME)
                                .email(USER_DETAILS_UPDATE_EMAIL)
                                .phone(USER_DETAILS_UPDATE_PHONE)
                                .build())
                .build();
    }

    public static UserDTO getUserDTOForUpdate(){
        return UserDTO.builder()
                .login(USER_UPDATE_LOGIN)
                .password(USER_UPDATE_PASSWORD)
                .repeatPassword(USER_UPDATE_REPEAT_PASSWORD)
                .userDetails(
                        UserDetailsDTO.builder()
                                .firstname(USER_DETAILS_UPDATE_FIRST_NAME)
                                .lastname(USER_DETAILS_UPDATE_LAST_NAME)
                                .email(USER_DETAILS_UPDATE_EMAIL)
                                .phone(USER_DETAILS_UPDATE_PHONE)
                                .build())
                .build();
    }

    public static UserDTO getUserDTOAfterUpdate(){
        return UserDTO.builder()
                .id(USER_1_ID)
                .login(USER_UPDATE_LOGIN)
                .password(USER_UPDATE_PASSWORD)
                .repeatPassword(USER_UPDATE_REPEAT_PASSWORD)
                .userDetails(
                        UserDetailsDTO.builder()
                                .firstname(USER_DETAILS_UPDATE_FIRST_NAME)
                                .lastname(USER_DETAILS_UPDATE_LAST_NAME)
                                .email(USER_DETAILS_UPDATE_EMAIL)
                                .phone(USER_DETAILS_UPDATE_PHONE)
                                .build())
                .userRole(USER_1_ROLE)
                .userStatus(USER_1_STATUS)
                .build();
    }

}
