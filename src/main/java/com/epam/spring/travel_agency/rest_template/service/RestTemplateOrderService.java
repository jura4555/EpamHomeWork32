package com.epam.spring.travel_agency.rest_template.service;

import com.epam.spring.travel_agency.controller.dto.OrderDTO;
import com.epam.spring.travel_agency.controller.model.OrderModel;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;
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
public class RestTemplateOrderService {

    private final RestTemplate restTemplate;

    public ResponseEntity<List<OrderDTO>> getAllOrder(){
        HttpEntity<OrderModel> entity = getEntityModel();
        ObjectMapper mapper = getObjectMapper();
        List<OrderModel> orderModels = mapper.convertValue(
                restTemplate.exchange(GET_ALL_ORDER_URL, HttpMethod.GET, entity, ArrayList.class).getBody(),
                new TypeReference<List<OrderModel>>() {});
        List<OrderDTO> orderDTOs = getOrderDTOs(orderModels);
        return new ResponseEntity<List<OrderDTO>>(orderDTOs, HttpStatus.OK);
    }

    public ResponseEntity<OrderDTO> getOrderByOrderId(int id){
        Map<String, Integer> param = new HashMap<>();
        param.put("id", id);
        return restTemplate.getForEntity(GET_ORDER_BY_ID_URL, OrderDTO.class, param);
    }

    public ResponseEntity<List<OrderDTO>> getOrderByTourStatus(TourStatus tourStatus){
        Map<String, TourStatus> param = new HashMap<>();
        param.put("tourStatus", tourStatus);
        HttpEntity<OrderModel> entity = getEntityModel();
        ObjectMapper mapper = getObjectMapper();
        List<OrderModel> orderModels = mapper.convertValue(
                restTemplate.exchange(GET_ORDER_BY_STATUS_URL, HttpMethod.GET, entity, ArrayList.class, param).getBody(),
                new TypeReference<List<OrderModel>>() {});
        List<OrderDTO> orderDTOs = getOrderDTOs(orderModels);
        return new ResponseEntity<List<OrderDTO>>(orderDTOs, HttpStatus.OK);
    }

    public ResponseEntity<OrderDTO> createOrder(OrderDTO orderDTO){
        return restTemplate.postForEntity(CREATE_ORDER_URL, orderDTO, OrderDTO.class);
    }

    public ResponseEntity<OrderDTO> updateOrderDescription(int id, String description){
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("description", description);
        restTemplatePatchSetting();
        HttpEntity<OrderDTO> entity = getEntityDTO();
        return restTemplate.exchange(UPDATE_ORDER_DESCRIPTION_URL, HttpMethod.PATCH, entity, OrderDTO.class, param);
    }

    public ResponseEntity<OrderDTO> updateOrderPrice(int id, int stepDisCount){
        Map<String, Integer> param = new HashMap<>();
        param.put("id", id);
        param.put("stepDisCount", stepDisCount);
        restTemplatePatchSetting();
        HttpEntity<OrderDTO> entity = getEntityDTO();
        return restTemplate.exchange(UPDATE_ORDER_PRICE_URL, HttpMethod.PATCH, entity, OrderDTO.class, param);
    }

    public ResponseEntity<OrderDTO> updateOrderStatus(int id, TourStatus tourStatus){
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("tourStatus", tourStatus);
        restTemplatePatchSetting();
        HttpEntity<OrderDTO> entity = getEntityDTO();
        return restTemplate.exchange(UPDATE_ORDER_STATUS_URL, HttpMethod.PATCH, entity, OrderDTO.class, param);
    }

    private HttpEntity<OrderModel> getEntityModel(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return new HttpEntity<OrderModel>(headers);
    }

    private HttpEntity<OrderDTO> getEntityDTO(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return new HttpEntity<OrderDTO>(headers);
    }

    private ObjectMapper getObjectMapper(){
        return new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    private void restTemplatePatchSetting(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        restTemplate.setRequestFactory(requestFactory);
    }

    private List<OrderDTO> getOrderDTOs(List<OrderModel> orderModels){
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (int i = 0; i < orderModels.size(); i++) {
            OrderDTO orderDTO = orderModels.get(i).getOrderDTO();
            orderDTOs.add(orderDTO);
        }
        return orderDTOs;
    }
}
