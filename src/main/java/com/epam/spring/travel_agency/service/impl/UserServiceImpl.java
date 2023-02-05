package com.epam.spring.travel_agency.service.impl;

import com.epam.spring.travel_agency.controller.dto.UserDTO;
import com.epam.spring.travel_agency.controller.dto.UserDetailsDTO;
import com.epam.spring.travel_agency.service.UserService;
import com.epam.spring.travel_agency.service.mapper.UserDetailsMapper;
import com.epam.spring.travel_agency.service.mapper.UserMapper;
import com.epam.spring.travel_agency.service.model.User;
import com.epam.spring.travel_agency.service.model.UserDetails;
import com.epam.spring.travel_agency.service.model.enums.UserRole;
import com.epam.spring.travel_agency.service.model.enums.UserStatus;
import com.epam.spring.travel_agency.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final UserDetailsMapper userDetailsMapper;

    @Override
    public UserDTO getUserByLogin(String login) {
        log.info("[Service] getUser by login {}", login);
        User user = userRepository.getUserByLogin(login);
        return userMapper.mapUserToUserDTO(user);
    }

    @Override
    public List<UserDTO> getUserByRole(UserRole userRole) {
        log.info("[Service] getUsers by userRole {}", userRole);
        List<User> myUser = userRepository.getUserByRole(userRole);
        return myUser.stream()
                .map(userMapper::mapUserToUserDTO)
                .sorted(Comparator.comparing(UserDTO::getLogin))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        log.info("[Service] createUser");
        User user = userMapper.mapUserDTOToUser(userDTO);
        user = userRepository.createUser(user);
        return userMapper.mapUserToUserDTO(user);
    }

    @Override
    public UserDTO updateUser(int id, UserDTO userDTO) {
        log.info("[Service] updateUser with all fields");
        User user = userMapper.mapUserDTOToUser(userDTO);
        User fullUser = userRepository.getUserById(id);
        user.setId(fullUser.getId());
        user.setUserRole(fullUser.getUserRole());
        user.setUserStatus(fullUser.getUserStatus());
        user = userRepository.updateUser(user);
        return userMapper.mapUserToUserDTO(user);
    }

    @Override
    public UserDTO updateUserRole(int id, UserRole userRole) {
        log.info("[Service] updateUser with userRole field");
        User user = userRepository.getUserById(id);
        user.setUserRole(userRole);
        user = userRepository.updateUser(user);
        return userMapper.mapUserToUserDTO(user);
    }

    @Override
    public UserDTO updateUserStatus(int id, UserStatus userStatus) {
        log.info("[Service] updateUser with userRole field");
        User user = userRepository.getUserById(id);
        user.setUserStatus(userStatus);
        user = userRepository.updateUser(user);
        return userMapper.mapUserToUserDTO(user);
    }

    @Override
    public UserDetailsDTO getUserDetails(int id) {
        log.info("[Service] getUserDetails by user id {} ", id);
        UserDetails userDetails = userRepository.getUserDetails(id);
        return userDetailsMapper.mapUserDetailsToUserDetailsDto(userDetails);
    }

}
