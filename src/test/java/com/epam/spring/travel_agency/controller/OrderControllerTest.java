package com.epam.spring.travel_agency.controller;

import com.epam.spring.travel_agency.controller.assembler.OrderAssembler;
import com.epam.spring.travel_agency.controller.dto.OrderDTO;
import com.epam.spring.travel_agency.service.OrderService;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;
import com.epam.spring.travel_agency.test.config.TestConfig;
import com.epam.spring.travel_agency.test.util.TestOrderDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

import static com.epam.spring.travel_agency.test.util.TestOrderDataUtil.ORDER_UPDATE_DESCRIPTION;
import static com.epam.spring.travel_agency.test.util.TestOrderDataUtil.ORDER_UPDATE_STEP_DIS_COUNT;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
@Import(TestConfig.class)
public class OrderControllerTest {

    @MockBean
    private OrderService orderService;

    @SpyBean
    private OrderAssembler orderAssembler;

    @Autowired
    private MockMvc mockMvc;



    @Test
    void getAllOrderTest() throws Exception{
        OrderDTO order1 = TestOrderDataUtil.getOrderDTO1();
        OrderDTO order2 = TestOrderDataUtil.getOrderDTO2();
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<OrderDTO> orders = new PageImpl<>(List.of(order1, order2), pageable, 2);
        when(orderService.getAllOrder(1, 2, "id", "asc")).thenReturn(orders);
        mockMvc.perform(get("/api/v1/order")
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
                        jsonPath("$.content[0].id").value(order1.getId()),
                        jsonPath("$.content[0].links").value(hasSize(5)),
                        jsonPath("$.content[1].id").value(order2.getId()),
                        jsonPath("$.content[1].links").value(hasSize(5))
                );
        verify(orderService, times(1)).getAllOrder(1, 2, "id", "asc");
        verify(orderAssembler, times(1)).toModel(order1);
        verify(orderAssembler, times(1)).toModel(order2);
    }

    @Test
    void getOrderByOrderId() throws Exception{
        OrderDTO order = TestOrderDataUtil.getOrderDTO1();
        when(orderService.getOrderByOrderId(order.getId())).thenReturn(order);
        mockMvc.perform(get("/api/v1/order/id/{id}", order.getId()))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(order.getId()),
                        jsonPath("$.user.id").value(order.getUser().getId()),
                        jsonPath("$.tour.id").value(order.getTour().getId()),
                        jsonPath("$.price").value(order.getPrice()),
                        jsonPath("$.stepDisCount").value(order.getStepDisCount()),
                        jsonPath("$.disCount").value(order.getDisCount()),
                        jsonPath("$.tourStatus").value(order.getTourStatus().name()),
                        jsonPath("$.description").value(order.getDescription()),
                        jsonPath("$.links").value(hasSize(5))
                        );
        verify(orderService, times(1)).getOrderByOrderId(order.getId());
        verify(orderAssembler, times(1)).toModel(order);
    }

    @Test
    void getOrderByTourStatusTest() throws Exception{
        OrderDTO order1 = TestOrderDataUtil.getOrderDTO1();
        OrderDTO order2 = TestOrderDataUtil.getOrderDTO2();
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<OrderDTO> orders = new PageImpl<>(List.of(order1, order2), pageable, 2);
        when(orderService.getOrderByTourStatus(TourStatus.REGISTERED,1, 2, "id", "asc")).thenReturn(orders);
        mockMvc.perform(get("/api/v1/order/tour/{tourStatus}", TourStatus.REGISTERED)
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
                        jsonPath("$.content[0].id").value(order1.getId()),
                        jsonPath("$.content[0].links").value(hasSize(5)),
                        jsonPath("$.content[1].id").value(order2.getId()),
                        jsonPath("$.content[1].links").value(hasSize(5))
                );
        verify(orderService, times(1)).getOrderByTourStatus(TourStatus.REGISTERED, 1, 2, "id", "asc");
        verify(orderAssembler, times(1)).toModel(order1);
        verify(orderAssembler, times(1)).toModel(order2);
    }

    @Test
    void createOrderTest() throws Exception{
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        OrderDTO createdOrder = TestOrderDataUtil.getOrderDTOForCreate();
        OrderDTO order = TestOrderDataUtil.getOrderDTO1();
        when(orderService.createOrder(createdOrder)).thenReturn(order);
        mockMvc.perform(post("/api/v1/order")
                        .contentType("application/json")
                        .content(jsonMapper.writeValueAsString(createdOrder)))
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(order.getId()),
                        jsonPath("$.user.id").value(order.getUser().getId()),
                        jsonPath("$.tour.id").value(order.getTour().getId()),
                        jsonPath("$.price").value(order.getPrice()),
                        jsonPath("$.stepDisCount").value(order.getStepDisCount()),
                        jsonPath("$.disCount").value(order.getDisCount()),
                        jsonPath("$.tourStatus").value(order.getTourStatus().name()),
                        jsonPath("$.description").value(order.getDescription()),
                        jsonPath("$.links").value(hasSize(5))
                );
        verify(orderService, times(1)).createOrder(createdOrder);
        verify(orderAssembler, times(1)).toModel(order);
    }

    @Test
    void updateOrderDescriptionTest() throws Exception{
        String newOrderDescription = ORDER_UPDATE_DESCRIPTION;
        OrderDTO order = TestOrderDataUtil.getOrderDTO1();
        order.setDescription(newOrderDescription);
        when(orderService.updateOrderDescription(order.getId(), newOrderDescription)).thenReturn(order);
        mockMvc.perform(patch("/api/v1/order/{id}/description", order.getId())
                        .queryParam("description", order.getDescription()))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(order.getId()),
                        jsonPath("$.user.id").value(order.getUser().getId()),
                        jsonPath("$.tour.id").value(order.getTour().getId()),
                        jsonPath("$.price").value(order.getPrice()),
                        jsonPath("$.stepDisCount").value(order.getStepDisCount()),
                        jsonPath("$.disCount").value(order.getDisCount()),
                        jsonPath("$.tourStatus").value(order.getTourStatus().name()),
                        jsonPath("$.description").value(order.getDescription()),
                        jsonPath("$.links").value(hasSize(5))
                );
        verify(orderService, times(1)).updateOrderDescription(order.getId(), newOrderDescription);
        verify(orderAssembler, times(1)).toModel(order);
    }

    @Test
    void updateOrderPriceTest() throws Exception{
        int newStepDisCount = ORDER_UPDATE_STEP_DIS_COUNT;
        OrderDTO order = TestOrderDataUtil.getOrderDTOForUpdatePrice();
        when(orderService.updateOrderPrice(order.getId(),newStepDisCount)).thenReturn(order);
        mockMvc.perform(patch("/api/v1/order/{id}/price", order.getId())
                        .queryParam("stepDisCount", String.valueOf(newStepDisCount)))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(order.getId()),
                        jsonPath("$.user.id").value(order.getUser().getId()),
                        jsonPath("$.tour.id").value(order.getTour().getId()),
                        jsonPath("$.price").value(order.getPrice()),
                        jsonPath("$.stepDisCount").value(order.getStepDisCount()),
                        jsonPath("$.disCount").value(order.getDisCount()),
                        jsonPath("$.tourStatus").value(order.getTourStatus().name()),
                        jsonPath("$.description").value(order.getDescription()),
                        jsonPath("$.links").value(hasSize(5))
                );
        verify(orderService, times(1)).updateOrderPrice(order.getId(), newStepDisCount);
        verify(orderAssembler, times(1)).toModel(order);
    }

    @Test
    void updateOrderStatusTest() throws Exception{
        TourStatus newTourStatus = TourStatus.PAID;
        OrderDTO order = TestOrderDataUtil.getOrderDTO1();
        order.setTourStatus(newTourStatus);
        order.getTour().setPlaceCount(order.getTour().getPlaceCount() - 1);
        when(orderService.updateOrderStatus(order.getId(), newTourStatus)).thenReturn(order);
        mockMvc.perform(patch("/api/v1/order/{id}/status", order.getId())
                        .queryParam("tourStatus", newTourStatus.toString()))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(order.getId()),
                        jsonPath("$.user.id").value(order.getUser().getId()),
                        jsonPath("$.tour.id").value(order.getTour().getId()),
                        jsonPath("$.price").value(order.getPrice()),
                        jsonPath("$.stepDisCount").value(order.getStepDisCount()),
                        jsonPath("$.disCount").value(order.getDisCount()),
                        jsonPath("$.tourStatus").value(order.getTourStatus().name()),
                        jsonPath("$.description").value(order.getDescription()),
                        jsonPath("$.links").value(hasSize(5))
                );
        verify(orderService, times(1)).updateOrderStatus(order.getId(), newTourStatus);
        verify(orderAssembler, times(1)).toModel(order);
    }

}
