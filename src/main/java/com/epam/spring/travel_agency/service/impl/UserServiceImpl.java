package com.epam.spring.travel_agency.service.impl;

import com.epam.spring.travel_agency.controller.dto.UserDTO;
import com.epam.spring.travel_agency.controller.dto.UserDetailsDTO;
import com.epam.spring.travel_agency.service.UserService;
import com.epam.spring.travel_agency.service.exception.*;
import com.epam.spring.travel_agency.service.mapper.UserDetailsMapper;
import com.epam.spring.travel_agency.service.mapper.UserMapper;
import com.epam.spring.travel_agency.service.model.User;
import com.epam.spring.travel_agency.service.model.UserDetails;
import com.epam.spring.travel_agency.service.model.enums.UserRole;
import com.epam.spring.travel_agency.service.model.enums.UserStatus;
import com.epam.spring.travel_agency.service.repository.UserDetailsRepository;
import com.epam.spring.travel_agency.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserDetailsRepository userDetailsRepository;

    private final UserMapper userMapper;

    private final UserDetailsMapper userDetailsMapper;

    @Override
    public UserDTO getUserByLogin(String login) {
        log.info("[Service] getUser by login {}", login);
        User user = userRepository.findByLogin(login).orElseThrow(UserNotFoundException::new);
        return userMapper.mapUserToUserDTO(user);
    }

    @Override
    public List<UserDTO> getUserByRole(UserRole userRole) {
        log.info("[Service] getUsers by userRole {}", userRole);
        List<User> myUser = userRepository.findByUserRole(userRole);
        return myUser.stream()
                .map(userMapper::mapUserToUserDTO)
                .sorted(Comparator.comparing(UserDTO::getLogin))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        log.info("[Service] createUser");
        if (userRepository.existsByLogin(userDTO.getLogin())) {
            throw new UserLoginAlreadyExistsException();
        }
        if(userDTO.getPassword().compareTo(userDTO.getRepeatPassword()) != 0){
            throw new UserRepeatPasswordException();
        }
        if(userDetailsRepository.existsByEmail(userDTO.getUserDetails().getEmail())){
            throw new UserEmailAlreadyExistsException();
        }
        if(userDetailsRepository.existsByPhone(userDTO.getUserDetails().getPhone())){
            throw new UserPhoneAlreadyExistsException();
        }
        User user = userMapper.mapUserDTOToUser(userDTO);
        user.setUserRole(UserRole.USER);
        user.setUserStatus(UserStatus.ACTIVE);
        user = userRepository.save(user);
        return userMapper.mapUserToUserDTO(user);
    }

    @Override
    public UserDTO updateUser(int id, UserDTO userDTO) {
        log.info("[Service] updateUser with all fields");
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException();
        }
        User dbUser = optionalUser.get();

        if(userDTO.getPassword().compareTo(userDTO.getRepeatPassword()) != 0){
            throw new UserRepeatPasswordException();
        }
        if(userDTO.getUserDetails().getEmail().compareTo(dbUser.getUserDetails().getEmail()) != 0){
            if(userDetailsRepository.existsByEmail(userDTO.getUserDetails().getEmail())){
                throw new UserEmailAlreadyExistsException();
            }
        }
        if(userDTO.getUserDetails().getPhone().compareTo(dbUser.getUserDetails().getPhone()) != 0){
            if(userDetailsRepository.existsByPhone(userDTO.getUserDetails().getPhone())){
                throw new UserPhoneAlreadyExistsException();
            }
        }
        User user = userMapper.mapUserDTOToUser(userDTO);
        user.setId(dbUser.getId());
        user.setUserRole(dbUser.getUserRole());
        user.setUserStatus(dbUser.getUserStatus());
        UserDetails userDetails = user.getUserDetails();
        userDetails.setId(dbUser.getUserDetails().getId());
        user.setUserDetails(userDetails);
        user = userRepository.save(user);
        return userMapper.mapUserToUserDTO(user);
    }

    @Override
    public UserDTO updateUserRole(int id, UserRole userRole) {
        log.info("[Service] updateUser with userRole field");
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();
        user.setUserRole(userRole);
        user = userRepository.save(user);
        return userMapper.mapUserToUserDTO(user);
    }

    @Override
    public UserDTO updateUserStatus(int id, UserStatus userStatus) {
        log.info("[Service] updateUser with userRole field");
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();
        user.setUserStatus(userStatus);
        user = userRepository.save(user);
        return userMapper.mapUserToUserDTO(user);
    }

    @Override
    public UserDetailsDTO getUserDetails(int id) {
        log.info("[Service] getUserDetails by user id {} ", id);
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException();
        }
        User user = optionalUser.get();
        UserDetails userDetails = user.getUserDetails();
        return userDetailsMapper.mapUserDetailsToUserDetailsDto(userDetails);
    }

}
