package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.controller.dto.UserDTO;
import com.epam.spring.homework3.controller.dto.UserDetailsDTO;
import com.epam.spring.homework3.service.UserService;
import com.epam.spring.homework3.service.mapper.UserDetailsMapper;
import com.epam.spring.homework3.service.mapper.UserMapper;
import com.epam.spring.homework3.service.model.User;
import com.epam.spring.homework3.service.model.UserDetails;
import com.epam.spring.homework3.service.model.enums.UserRole;
import com.epam.spring.homework3.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
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
    public UserDTO updateUser(UserDTO userDTO) {
        log.info("[Service] updateUser with all fields");
        User user = userMapper.mapUserDTOToUser(userDTO);
        user = userRepository.updateUser(user);
        return userMapper.mapUserToUserDTO(user);
    }

    @Override
    public UserDetailsDTO getUserDetails(int userId) {
        log.info("[Service] getUserDetails by user id {} ", userId);
        UserDetails userDetails = userRepository.getUserDetails(userId);
        return userDetailsMapper.mapUserDetailsToUserDetailsDto(userDetails);
    }

}
