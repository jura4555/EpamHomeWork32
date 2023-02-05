package com.epam.spring.travel_agency.rest_template.service;

import com.epam.spring.travel_agency.controller.dto.UserDTO;
import com.epam.spring.travel_agency.controller.dto.UserDetailsDTO;
import com.epam.spring.travel_agency.controller.model.UserModel;
import com.epam.spring.travel_agency.service.model.enums.UserRole;
import com.epam.spring.travel_agency.service.model.enums.UserStatus;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.epam.spring.travel_agency.rest_template.service.reference.RestTemplateReference.*;

@Service
@RequiredArgsConstructor
public class RestTemplateUserService {

    private final RestTemplate restTemplate;

    public ResponseEntity<UserDTO> getUserByLogin(String login){
        Map<String, String> param = new HashMap<>();
        param.put("login", login);
        return restTemplate.getForEntity(GET_USER_BY_LOGIN_URL, UserDTO.class, param);
    }

    public ResponseEntity<List<UserDTO>> getUserByRole(UserRole userRole){
        Map<String, UserRole> param = new HashMap<>();
        param.put("userRole", userRole);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <UserModel> entity = new HttpEntity<UserModel>(headers);
        ObjectMapper mapper = new ObjectMapper();
        List<UserModel> userModels = mapper.convertValue(
                restTemplate.exchange(GET_USER_BY_ROLE_URL, HttpMethod.GET, entity, ArrayList.class, param).getBody(),
                new TypeReference<List<UserModel>>() {});
        List<UserDTO> userDTOs = new ArrayList<>();
        for (int i = 0; i < userModels.size(); i++) {
            UserDTO userDTO = userModels.get(i).getUserDTO();
            userDTOs.add(userDTO);
        }
        return new ResponseEntity<List<UserDTO>>(userDTOs, HttpStatus.OK);
    }

    public ResponseEntity<UserDTO> createUser(UserDTO userDTO){
        return restTemplate.postForEntity(CREATE_USER_URL, userDTO, UserDTO.class);
    }

    public ResponseEntity<UserDTO> updateUser(int id, UserDTO userDTO){
        Map<String, Integer> param = new HashMap<>();
        param.put("id", id);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDTO> entity = new HttpEntity<UserDTO>(userDTO, headers);
        return restTemplate.exchange(UPDATE_USER_URL, HttpMethod.PUT, entity, UserDTO.class, param);
    }

    public ResponseEntity<UserDTO> updateUserRole(int id, UserRole userRole){
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("userRole", userRole);
        restTemplatePatchSetting();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDTO> entity = new HttpEntity<UserDTO>(headers);
        return restTemplate.exchange(UPDATE_USER_ROLE_URL, HttpMethod.PATCH, entity, UserDTO.class, param);
    }

    public ResponseEntity<UserDTO> updateUserStatus(int id, UserStatus userStatus){
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("userStatus", userStatus);
        restTemplatePatchSetting();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDTO> entity = new HttpEntity<UserDTO>(headers);
        return restTemplate.exchange(UPDATE_USER_STATUS_URL, HttpMethod.PATCH, entity, UserDTO.class, param);
    }

    public ResponseEntity<UserDetailsDTO> getUserDetails(int id){
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDetailsDTO> entity = new HttpEntity<UserDetailsDTO>(headers);
        return restTemplate.exchange(GET_USER_DETAILS_URL, HttpMethod.GET, entity, UserDetailsDTO.class, param);
    }

    private void restTemplatePatchSetting(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate.setRequestFactory(requestFactory);
    }
}
