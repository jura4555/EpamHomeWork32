package com.epam.spring.travel_agency.service;

import com.epam.spring.travel_agency.controller.dto.OrderDTO;
import com.epam.spring.travel_agency.service.exception.MaxDisCountNotFound;
import com.epam.spring.travel_agency.service.exception.OrderNotFoundException;
import com.epam.spring.travel_agency.service.exception.OrderStatusException;
import com.epam.spring.travel_agency.service.exception.PriceNotFoundException;
import com.epam.spring.travel_agency.service.impl.OrderServiceImpl;
import com.epam.spring.travel_agency.service.impl.randomizers.MyDisCountRandomizer;
import com.epam.spring.travel_agency.service.mapper.OrderMapper;
import com.epam.spring.travel_agency.service.model.Order;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;
import com.epam.spring.travel_agency.service.repository.OrderRepository;
import com.epam.spring.travel_agency.test.util.TestOrderDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static com.epam.spring.travel_agency.test.util.TestOrderDataUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private MyDisCountRandomizer myDisCountRandomizer;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void getAllOrderTest(){
        Order order1 = TestOrderDataUtil.getOrder1();
        Order order2 = TestOrderDataUtil.getOrder2();
        OrderDTO orderDTO1 = TestOrderDataUtil.getOrderDTO1();
        OrderDTO orderDTO2 = TestOrderDataUtil.getOrderDTO2();
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<Order> orders = new PageImpl<>(List.of(order1, order2), pageable, 2);
        when(orderRepository.findAll(pageable)).thenReturn(orders);
        when(orderMapper.mapOrderToOrderDTO(order1)).thenReturn(orderDTO1);
        when(orderMapper.mapOrderToOrderDTO(order2)).thenReturn(orderDTO2);
        Page<OrderDTO> result = orderService.getAllOrder(1, 2, "id", "asc");
        assertThat(result.getTotalElements(), is(orders.getTotalElements()));
        assertThat(result, contains(orderDTO1, orderDTO2));
    }

    @Test
    void getOrdersByTourStatusTest(){
        Order order1 = TestOrderDataUtil.getOrder1();
        Order order2 = TestOrderDataUtil.getOrder2();
        OrderDTO orderDTO1 = TestOrderDataUtil.getOrderDTO1();
        OrderDTO orderDTO2 = TestOrderDataUtil.getOrderDTO2();
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<Order> orders = new PageImpl<>(List.of(order1, order2), pageable, 2);
        when(orderRepository.findByTourStatus(TourStatus.REGISTERED, pageable)).thenReturn(orders);
        when(orderMapper.mapOrderToOrderDTO(order1)).thenReturn(orderDTO1);
        when(orderMapper.mapOrderToOrderDTO(order2)).thenReturn(orderDTO2);
        Page<OrderDTO> result = orderService.getOrderByTourStatus(TourStatus.REGISTERED, 1, 2, "id", "asc");
        assertThat(result.getTotalElements(), is(orders.getTotalElements()));
        assertThat(result, contains(orderDTO1, orderDTO2));
    }

    @Test
    void getOrderByOrderIdTest(){
        Order order = TestOrderDataUtil.getOrder1();
        OrderDTO orderDTO = TestOrderDataUtil.getOrderDTO1();
        when(orderRepository.findById(ORDER_1_ID)).thenReturn(Optional.of(order));
        when(orderMapper.mapOrderToOrderDTO(order)).thenReturn(orderDTO);
        OrderDTO result = orderService.getOrderByOrderId(ORDER_1_ID);
        assertThat(result, allOf(
                hasProperty("id", equalTo(orderDTO.getId())),
                hasProperty("tourStatus", equalTo(orderDTO.getTourStatus())),
                hasProperty("description", equalTo(orderDTO.getDescription()))
        ));
        assertThat(result.getTour(), allOf(
                hasProperty("id", equalTo(orderDTO.getTour().getId())),
                hasProperty("name", equalTo(orderDTO.getTour().getName())),
                hasProperty("price", equalTo(orderDTO.getTour().getPrice())),
                hasProperty("dateDaparture", equalTo(orderDTO.getTour().getDateDaparture())),
                hasProperty("dateArrival", equalTo(orderDTO.getTour().getDateArrival())),
                hasProperty("placeDaparture", equalTo(orderDTO.getTour().getPlaceDaparture())),
                hasProperty("placeArrival", equalTo(orderDTO.getTour().getPlaceArrival())),
                hasProperty("maxDisCount", equalTo(orderDTO.getTour().getMaxDisCount())),
                hasProperty("placeCount", equalTo(orderDTO.getTour().getPlaceCount())),
                hasProperty("tourType", equalTo(orderDTO.getTour().getTourType())),
                hasProperty("burning", equalTo(orderDTO.getTour().isBurning()))
        ));
        assertThat(result.getTour().getHotel(), allOf(
                hasProperty("id", equalTo(orderDTO.getTour().getHotel().getId())),
                hasProperty("name", equalTo(orderDTO.getTour().getHotel().getName())),
                hasProperty("city", equalTo(orderDTO.getTour().getHotel().getCity())),
                hasProperty("hotelType", equalTo(orderDTO.getTour().getHotel().getHotelType()))
        ));
        assertThat(result.getUser(), allOf(
                hasProperty("id", equalTo(orderDTO.getUser().getId())),
                hasProperty("login", equalTo(orderDTO.getUser().getLogin())),
                hasProperty("password", equalTo(orderDTO.getUser().getPassword())),
                hasProperty("userRole", equalTo(orderDTO.getUser().getUserRole())),
                hasProperty("userStatus", equalTo(orderDTO.getUser().getUserStatus()))
        ));
        assertThat(result.getUser().getUserDetails(), allOf(
                hasProperty("firstname", equalTo(orderDTO.getUser().getUserDetails().getFirstname())),
                hasProperty("lastname", equalTo(orderDTO.getUser().getUserDetails().getLastname())),
                hasProperty("email", equalTo(orderDTO.getUser().getUserDetails().getEmail())),
                hasProperty("phone", equalTo(orderDTO.getUser().getUserDetails().getPhone()))
        ));
    }

    @Test
    void getOrderByOrderIdWithOrderNotFoundExceptionTest(){
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderByOrderId(anyInt()));
    }

    @Test
    void createOrderTest(){
        Order order = TestOrderDataUtil.getOrder1();
        OrderDTO orderDTO = TestOrderDataUtil.getOrderDTOForCreate();
        when(orderMapper.mapOrderDTOToOrder(orderDTO)).thenReturn(TestOrderDataUtil.getOrderForCreate());
        when(orderRepository.save(order)).thenReturn(order);
        when(orderMapper.mapOrderToOrderDTO(order)).thenReturn(TestOrderDataUtil.getOrderDTO1());
        OrderDTO result = orderService.createOrder(orderDTO);
        assertThat(result, allOf(
                hasProperty("id", equalTo(orderDTO.getId())),
                hasProperty("tourStatus", equalTo(TourStatus.REGISTERED)),
                hasProperty("description", equalTo(orderDTO.getDescription()))
        ));
        assertThat(result.getTour(), allOf(
                hasProperty("id", equalTo(orderDTO.getTour().getId())),
                hasProperty("name", equalTo(orderDTO.getTour().getName())),
                hasProperty("price", equalTo(orderDTO.getTour().getPrice())),
                hasProperty("dateDaparture", equalTo(orderDTO.getTour().getDateDaparture())),
                hasProperty("dateArrival", equalTo(orderDTO.getTour().getDateArrival())),
                hasProperty("placeDaparture", equalTo(orderDTO.getTour().getPlaceDaparture())),
                hasProperty("placeArrival", equalTo(orderDTO.getTour().getPlaceArrival())),
                hasProperty("maxDisCount", equalTo(orderDTO.getTour().getMaxDisCount())),
                hasProperty("placeCount", equalTo(orderDTO.getTour().getPlaceCount())),
                hasProperty("tourType", equalTo(orderDTO.getTour().getTourType())),
                hasProperty("burning", equalTo(orderDTO.getTour().isBurning()))
        ));
        assertThat(result.getTour().getHotel(), allOf(
                hasProperty("id", equalTo(orderDTO.getTour().getHotel().getId())),
                hasProperty("name", equalTo(orderDTO.getTour().getHotel().getName())),
                hasProperty("city", equalTo(orderDTO.getTour().getHotel().getCity())),
                hasProperty("hotelType", equalTo(orderDTO.getTour().getHotel().getHotelType()))
        ));
        assertThat(result.getUser(), allOf(
                hasProperty("id", equalTo(orderDTO.getUser().getId())),
                hasProperty("login", equalTo(orderDTO.getUser().getLogin())),
                hasProperty("password", equalTo(orderDTO.getUser().getPassword())),
                hasProperty("userRole", equalTo(orderDTO.getUser().getUserRole())),
                hasProperty("userStatus", equalTo(orderDTO.getUser().getUserStatus()))
        ));
        assertThat(result.getUser().getUserDetails(), allOf(
                hasProperty("firstname", equalTo(orderDTO.getUser().getUserDetails().getFirstname())),
                hasProperty("lastname", equalTo(orderDTO.getUser().getUserDetails().getLastname())),
                hasProperty("email", equalTo(orderDTO.getUser().getUserDetails().getEmail())),
                hasProperty("phone", equalTo(orderDTO.getUser().getUserDetails().getPhone()))
        ));
    }

    @Test
    void updateOrderDescriptionTest(){
        Order order = TestOrderDataUtil.getOrder1();
        OrderDTO orderDTO = TestOrderDataUtil.getOrderDTOForUpdate();
        when(orderRepository.findById(ORDER_1_ID)).thenReturn(Optional.of(order));
        when(orderRepository.save(TestOrderDataUtil.getOrderForUpdate())).thenReturn(TestOrderDataUtil.getOrderForUpdate());
        when(orderMapper.mapOrderToOrderDTO(TestOrderDataUtil.getOrderForUpdate())).thenReturn(orderDTO);
        OrderDTO result = orderService.updateOrderDescription(ORDER_1_ID, ORDER_UPDATE_DESCRIPTION);
        assertThat(result, allOf(
                hasProperty("id", equalTo(orderDTO.getId())),
                hasProperty("tourStatus", equalTo(TourStatus.REGISTERED)),
                hasProperty("description", equalTo(orderDTO.getDescription()))
        ));
        assertThat(result.getTour(), allOf(
                hasProperty("id", equalTo(orderDTO.getTour().getId())),
                hasProperty("name", equalTo(orderDTO.getTour().getName())),
                hasProperty("price", equalTo(orderDTO.getTour().getPrice())),
                hasProperty("dateDaparture", equalTo(orderDTO.getTour().getDateDaparture())),
                hasProperty("dateArrival", equalTo(orderDTO.getTour().getDateArrival())),
                hasProperty("placeDaparture", equalTo(orderDTO.getTour().getPlaceDaparture())),
                hasProperty("placeArrival", equalTo(orderDTO.getTour().getPlaceArrival())),
                hasProperty("maxDisCount", equalTo(orderDTO.getTour().getMaxDisCount())),
                hasProperty("placeCount", equalTo(orderDTO.getTour().getPlaceCount())),
                hasProperty("tourType", equalTo(orderDTO.getTour().getTourType())),
                hasProperty("burning", equalTo(orderDTO.getTour().isBurning()))
        ));
        assertThat(result.getTour().getHotel(), allOf(
                hasProperty("id", equalTo(orderDTO.getTour().getHotel().getId())),
                hasProperty("name", equalTo(orderDTO.getTour().getHotel().getName())),
                hasProperty("city", equalTo(orderDTO.getTour().getHotel().getCity())),
                hasProperty("hotelType", equalTo(orderDTO.getTour().getHotel().getHotelType()))
        ));
        assertThat(result.getUser(), allOf(
                hasProperty("id", equalTo(orderDTO.getUser().getId())),
                hasProperty("login", equalTo(orderDTO.getUser().getLogin())),
                hasProperty("password", equalTo(orderDTO.getUser().getPassword())),
                hasProperty("userRole", equalTo(orderDTO.getUser().getUserRole())),
                hasProperty("userStatus", equalTo(orderDTO.getUser().getUserStatus()))
        ));
        assertThat(result.getUser().getUserDetails(), allOf(
                hasProperty("firstname", equalTo(orderDTO.getUser().getUserDetails().getFirstname())),
                hasProperty("lastname", equalTo(orderDTO.getUser().getUserDetails().getLastname())),
                hasProperty("email", equalTo(orderDTO.getUser().getUserDetails().getEmail())),
                hasProperty("phone", equalTo(orderDTO.getUser().getUserDetails().getPhone()))
        ));
    }

    @Test
    void updateOrderDescriptionWithOrderNotFoundExceptionTest(){
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> orderService.updateOrderDescription(anyInt(), ORDER_UPDATE_DESCRIPTION));
    }

    @Test
    void updateOrderPriceTest(){
        Order order = getOrderForUpdatePrice();
        OrderDTO orderDTO = getOrderDTOForUpdatePrice();
        when(orderRepository.findById(ORDER_1_ID)).thenReturn(Optional.of(getOrder1()));
        when(myDisCountRandomizer.getRandomDisCount(20, 5)).thenReturn(10);
        when(orderRepository.save(order)).thenReturn(order);
        when(orderMapper.mapOrderToOrderDTO(order)).thenReturn(orderDTO);
        OrderDTO result = orderService.updateOrderPrice(ORDER_1_ID, ORDER_UPDATE_STEP_DIS_COUNT);
        assertThat(result, allOf(
                hasProperty("id", equalTo(orderDTO.getId())),
                hasProperty("price", equalTo(orderDTO.getPrice())),
                hasProperty("stepDisCount", equalTo(orderDTO.getStepDisCount())),
                hasProperty("disCount", equalTo(orderDTO.getDisCount())),
                hasProperty("tourStatus", equalTo(TourStatus.REGISTERED)),
                hasProperty("description", equalTo(orderDTO.getDescription()))
        ));
        assertThat(result.getTour(), allOf(
                hasProperty("id", equalTo(orderDTO.getTour().getId())),
                hasProperty("name", equalTo(orderDTO.getTour().getName())),
                hasProperty("price", equalTo(orderDTO.getTour().getPrice())),
                hasProperty("dateDaparture", equalTo(orderDTO.getTour().getDateDaparture())),
                hasProperty("dateArrival", equalTo(orderDTO.getTour().getDateArrival())),
                hasProperty("placeDaparture", equalTo(orderDTO.getTour().getPlaceDaparture())),
                hasProperty("placeArrival", equalTo(orderDTO.getTour().getPlaceArrival())),
                hasProperty("maxDisCount", equalTo(orderDTO.getTour().getMaxDisCount())),
                hasProperty("placeCount", equalTo(orderDTO.getTour().getPlaceCount())),
                hasProperty("tourType", equalTo(orderDTO.getTour().getTourType())),
                hasProperty("burning", equalTo(orderDTO.getTour().isBurning()))
        ));
        assertThat(result.getTour().getHotel(), allOf(
                hasProperty("id", equalTo(orderDTO.getTour().getHotel().getId())),
                hasProperty("name", equalTo(orderDTO.getTour().getHotel().getName())),
                hasProperty("city", equalTo(orderDTO.getTour().getHotel().getCity())),
                hasProperty("hotelType", equalTo(orderDTO.getTour().getHotel().getHotelType()))
        ));
        assertThat(result.getUser(), allOf(
                hasProperty("id", equalTo(orderDTO.getUser().getId())),
                hasProperty("login", equalTo(orderDTO.getUser().getLogin())),
                hasProperty("password", equalTo(orderDTO.getUser().getPassword())),
                hasProperty("userRole", equalTo(orderDTO.getUser().getUserRole())),
                hasProperty("userStatus", equalTo(orderDTO.getUser().getUserStatus()))
        ));
        assertThat(result.getUser().getUserDetails(), allOf(
                hasProperty("firstname", equalTo(orderDTO.getUser().getUserDetails().getFirstname())),
                hasProperty("lastname", equalTo(orderDTO.getUser().getUserDetails().getLastname())),
                hasProperty("email", equalTo(orderDTO.getUser().getUserDetails().getEmail())),
                hasProperty("phone", equalTo(orderDTO.getUser().getUserDetails().getPhone()))
        ));
    }

    @Test
    void updateOrderPriceWithOrderNotFoundExceptionTest(){
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> orderService.updateOrderPrice(anyInt(), ORDER_UPDATE_STEP_DIS_COUNT));
    }

    @Test
    void updateOrderPriceWithMaxDisCountNotFoundTest() {
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(getOrderForMaxDisCountException()));
        assertThrows(MaxDisCountNotFound.class, () -> orderService.updateOrderPrice(anyInt(), ORDER_UPDATE_STEP_DIS_COUNT));
    }

    @Test
    void updateOrderStatusTest(){
        Order order = TestOrderDataUtil.getOrderForUpdatePrice();
        OrderDTO orderDTO = TestOrderDataUtil.getOrderDTOForUpdateStatus();
        when(orderRepository.findById(ORDER_1_ID)).thenReturn(Optional.of(order));
        when(orderRepository.save(TestOrderDataUtil.getOrderForUpdateStatus())).thenReturn(TestOrderDataUtil.getOrderForUpdateStatus());
        when(orderMapper.mapOrderToOrderDTO(TestOrderDataUtil.getOrderForUpdateStatus())).thenReturn(orderDTO);
        OrderDTO result = orderService.updateOrderStatus(ORDER_1_ID, TourStatus.PAID);
        assertThat(result, allOf(
                hasProperty("id", equalTo(orderDTO.getId())),
                hasProperty("price", equalTo(orderDTO.getPrice())),
                hasProperty("stepDisCount", equalTo(orderDTO.getStepDisCount())),
                hasProperty("disCount", equalTo(orderDTO.getDisCount())),
                hasProperty("tourStatus", equalTo(orderDTO.getTourStatus())),
                hasProperty("description", equalTo(orderDTO.getDescription()))
        ));
        assertThat(result.getTour(), allOf(
                hasProperty("id", equalTo(orderDTO.getTour().getId())),
                hasProperty("name", equalTo(orderDTO.getTour().getName())),
                hasProperty("price", equalTo(orderDTO.getTour().getPrice())),
                hasProperty("dateDaparture", equalTo(orderDTO.getTour().getDateDaparture())),
                hasProperty("dateArrival", equalTo(orderDTO.getTour().getDateArrival())),
                hasProperty("placeDaparture", equalTo(orderDTO.getTour().getPlaceDaparture())),
                hasProperty("placeArrival", equalTo(orderDTO.getTour().getPlaceArrival())),
                hasProperty("maxDisCount", equalTo(orderDTO.getTour().getMaxDisCount())),
                hasProperty("placeCount", equalTo(orderDTO.getTour().getPlaceCount())),
                hasProperty("tourType", equalTo(orderDTO.getTour().getTourType())),
                hasProperty("burning", equalTo(orderDTO.getTour().isBurning()))
        ));
        assertThat(result.getTour().getHotel(), allOf(
                hasProperty("id", equalTo(orderDTO.getTour().getHotel().getId())),
                hasProperty("name", equalTo(orderDTO.getTour().getHotel().getName())),
                hasProperty("city", equalTo(orderDTO.getTour().getHotel().getCity())),
                hasProperty("hotelType", equalTo(orderDTO.getTour().getHotel().getHotelType()))
        ));
        assertThat(result.getUser(), allOf(
                hasProperty("id", equalTo(orderDTO.getUser().getId())),
                hasProperty("login", equalTo(orderDTO.getUser().getLogin())),
                hasProperty("password", equalTo(orderDTO.getUser().getPassword())),
                hasProperty("userRole", equalTo(orderDTO.getUser().getUserRole())),
                hasProperty("userStatus", equalTo(orderDTO.getUser().getUserStatus()))
        ));
        assertThat(result.getUser().getUserDetails(), allOf(
                hasProperty("firstname", equalTo(orderDTO.getUser().getUserDetails().getFirstname())),
                hasProperty("lastname", equalTo(orderDTO.getUser().getUserDetails().getLastname())),
                hasProperty("email", equalTo(orderDTO.getUser().getUserDetails().getEmail())),
                hasProperty("phone", equalTo(orderDTO.getUser().getUserDetails().getPhone()))
        ));
    }

    @Test
    void updateOrderStatusWithOrderNotFoundExceptionTest(){
        when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(OrderNotFoundException.class, () -> orderService.updateOrderStatus(anyInt(), TourStatus.PAID));
    }

    @Test
    void updateOrderStatusWithPriceNotFoundExceptionTest(){
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(TestOrderDataUtil.getOrder1()));
        assertThrows(PriceNotFoundException.class, () -> orderService.updateOrderStatus(anyInt(), TourStatus.PAID));
    }

    @Test
    void updateOrderStatusWithOrderStatusExceptionTest(){
        Order order = TestOrderDataUtil.getOrderForUpdatePrice();
        order.setTourStatus(TourStatus.PAID);
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
        assertThrows(OrderStatusException.class, () -> orderService.updateOrderStatus(anyInt(), TourStatus.PAID));
    }
}
