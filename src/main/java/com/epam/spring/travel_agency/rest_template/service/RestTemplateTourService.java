package com.epam.spring.travel_agency.rest_template.service;

import com.epam.spring.travel_agency.controller.dto.TourDTO;
import com.epam.spring.travel_agency.controller.model.TourModel;
import com.epam.spring.travel_agency.service.model.enums.HotelType;
import com.epam.spring.travel_agency.service.model.enums.TourType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
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
public class RestTemplateTourService {

    private final RestTemplate restTemplate;

    public ResponseEntity<List<TourDTO>> getAllTour(){
        HttpEntity<TourModel> entity = getEntityModel();
        ObjectMapper mapper = getObjectMapper();
        List<TourModel> tourModels = mapper.convertValue(
                restTemplate.exchange(GET_ALL_TOUR_URL, HttpMethod.GET, entity, ArrayList.class).getBody(),
                new TypeReference<List<TourModel>>() {});
        List<TourDTO> tourDTOs = getTourDTOs(tourModels);
        return new ResponseEntity<List<TourDTO>>(tourDTOs, HttpStatus.OK);
    }

    public ResponseEntity<TourDTO> getTourByName(String name){
        Map<String, String> param = new HashMap<>();
        param.put("name", name);
        return restTemplate.getForEntity(GET_TOUR_BY_NAME_URL, TourDTO.class, param);
    }

    public ResponseEntity<List<TourDTO>> getTourByTourType(TourType tourType){
        Map<String, TourType> param = new HashMap<>();
        param.put("tourType", tourType);
        HttpEntity<TourModel> entity = getEntityModel();
        ObjectMapper mapper = getObjectMapper();
        List<TourModel> tourModels = mapper.convertValue(
                restTemplate.exchange(GET_TOUR_BY_TYPE_URL, HttpMethod.GET, entity, ArrayList.class, param).getBody(),
                new TypeReference<List<TourModel>>() {});
        List<TourDTO> tourDTOs = getTourDTOs(tourModels);
        return new ResponseEntity<List<TourDTO>>(tourDTOs, HttpStatus.OK);
    }

    public ResponseEntity<List<TourDTO>> getTourByPlaceCount(int count){
        Map<String, Integer> param = new HashMap<>();
        param.put("count", count);
        HttpEntity<TourModel> entity = getEntityModel();
        ObjectMapper mapper = getObjectMapper();
        List<TourModel> tourModels = mapper.convertValue(
                restTemplate.exchange(GET_TOUR_BY_COUNT_URL, HttpMethod.GET, entity, ArrayList.class, param).getBody(),
                new TypeReference<List<TourModel>>() {});
        List<TourDTO> tourDTOs = getTourDTOs(tourModels);
        return new ResponseEntity<List<TourDTO>>(tourDTOs, HttpStatus.OK);
    }

    public ResponseEntity<List<TourDTO>> getTourByPrice(int minPrice, int maxPrice){
        Map<String, Integer> param = new HashMap<>();
        param.put("minPrice", minPrice);
        param.put("maxPrice", maxPrice);
        HttpEntity<TourModel> entity = getEntityModel();
        ObjectMapper mapper = getObjectMapper();
        List<TourModel> tourModels = mapper.convertValue(
                restTemplate.exchange(GET_TOUR_BY_PRICE_URL, HttpMethod.GET, entity, ArrayList.class, param).getBody(),
                new TypeReference<List<TourModel>>() {});
        List<TourDTO> tourDTOs = getTourDTOs(tourModels);
        return new ResponseEntity<List<TourDTO>>(tourDTOs, HttpStatus.OK);
    }

    public ResponseEntity<List<TourDTO>> getTourByHotelType(HotelType hotelType){
        Map<String, HotelType> param = new HashMap<>();
        param.put("hotelType", hotelType);
        HttpEntity<TourModel> entity = getEntityModel();
        ObjectMapper mapper = getObjectMapper();
        List<TourModel> tourModels = mapper.convertValue(
                restTemplate.exchange(GET_TOUR_BY_HOTEL_TYPE_URL, HttpMethod.GET, entity, ArrayList.class, param).getBody(),
                new TypeReference<List<TourModel>>() {});
        List<TourDTO> tourDTOs = getTourDTOs(tourModels);
        return new ResponseEntity<List<TourDTO>>(tourDTOs, HttpStatus.OK);
    }

    public ResponseEntity<TourDTO> createTour(TourDTO tourDTO){
        return restTemplate.postForEntity(CREATE_TOUR_URL, tourDTO, TourDTO.class);
    }

    public ResponseEntity<TourDTO> updateTour(int id, TourDTO tourDTO){
        Map<String, Integer> param = new HashMap<>();
        param.put("id", id);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<TourDTO> entity = new HttpEntity<TourDTO>(tourDTO, headers);
        return restTemplate.exchange(UPDATE_TOUR_URL, HttpMethod.PUT, entity, TourDTO.class, param);
    }

    public ResponseEntity<TourDTO> updateTourBurning(int id, boolean burning){
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("burning", burning);
        restTemplatePatchSetting();
        HttpEntity<TourDTO> entity = getEntityDTO();
        return restTemplate.exchange(UPDATE_TOUR_BURNING_URL, HttpMethod.PATCH, entity, TourDTO.class, param);
    }

    public ResponseEntity<TourDTO> updateTourMaxDisCount(int id, int maxDisCount){
        Map<String, Integer> param = new HashMap<>();
        param.put("id", id);
        param.put("maxDisCount", maxDisCount);
        restTemplatePatchSetting();
        HttpEntity<TourDTO> entity = getEntityDTO();
        return restTemplate.exchange(UPDATE_TOUR_MAXDISCOUNT_URL, HttpMethod.PATCH, entity, TourDTO.class, param);
    }

    public void deleteTour(int id){
        Map<String, Integer> param = new HashMap<>();
        param.put("id", id);
        restTemplate.delete(DELETE_TOUR_URL, param);
    }

    private HttpEntity<TourModel> getEntityModel(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return new HttpEntity<TourModel>(headers);
    }

    private HttpEntity<TourDTO> getEntityDTO(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return new HttpEntity<TourDTO>(headers);
    }

    private void restTemplatePatchSetting(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate.setRequestFactory(requestFactory);
    }

    private ObjectMapper getObjectMapper(){
        return new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    private List<TourDTO> getTourDTOs(List<TourModel> tourModels){
        List<TourDTO> tourDTOs = new ArrayList<>();
        for (int i = 0; i < tourModels.size(); i++) {
            TourDTO tourDTO = tourModels.get(i).getTourDTO();
            tourDTOs.add(tourDTO);
        }
        return tourDTOs;
    }

}
