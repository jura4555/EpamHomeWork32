package com.epam.spring.travel_agency.rest_template.service;

import com.epam.spring.travel_agency.controller.dto.HotelDTO;
import com.epam.spring.travel_agency.controller.model.HotelModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static com.epam.spring.travel_agency.rest_template.service.reference.RestTemplateReference.*;


@Service
@RequiredArgsConstructor
public class RestTemplateHotelService {

    private final RestTemplate restTemplate;

    public ResponseEntity<List<HotelDTO>> getAllHotel(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <HotelModel> entity = new HttpEntity<HotelModel>(headers);
        ObjectMapper mapper = new ObjectMapper();
        List<HotelModel> hotelModels = mapper.convertValue(
                restTemplate.exchange(GET_ALL_HOTEL_URL, HttpMethod.GET, entity, ArrayList.class).getBody(),
                new TypeReference<List<HotelModel>>() {});
        List<HotelDTO> hotelDTOs = new ArrayList<>();
        for (int i = 0; i < hotelModels.size(); i++) {
            HotelDTO hotelDTO = hotelModels.get(i).getHotelDTO();
            hotelDTOs.add(hotelDTO);
        }
        return new ResponseEntity<List<HotelDTO>>(hotelDTOs, HttpStatus.OK);
    }

    public ResponseEntity<HotelDTO> getHotelByName(String name){
        Map<String, String> param = new HashMap<>();
        param.put("name", name);
        return restTemplate.getForEntity(GET_HOTEL_BY_NAME_URL, HotelDTO.class, param);
    }


    public ResponseEntity<HotelDTO> createHotel(HotelDTO hotelDTO){
        return restTemplate.postForEntity(CREATE_HOTEL_URL, hotelDTO, HotelDTO.class);
    }

    public ResponseEntity<HotelDTO> updateHotel(int id, HotelDTO hotelDTO){
        Map<String, Integer> param = new HashMap<>();
        param.put("id", id);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<HotelDTO> entity = new HttpEntity<HotelDTO>(hotelDTO, headers);
        return restTemplate.exchange(UPDATE_HOTEL_URL, HttpMethod.PUT, entity, HotelDTO.class, param);
    }


    public void deleteHotel(int id){
        Map<String, Integer> param = new HashMap<>();
        param.put("id", id);
        restTemplate.delete(DELETE_HOTEL_URL, param);
    }

}
