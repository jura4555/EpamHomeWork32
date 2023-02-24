package com.epam.spring.travel_agency.service;

import com.epam.spring.travel_agency.controller.dto.OrderDTO;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    Page<OrderDTO> getAllOrder(int page, int size, String sortBy, String order);

    Page<OrderDTO> getOrderByTourStatus(TourStatus tourStatus, int page, int size, String sortBy, String order);

    OrderDTO getOrderByOrderId(int orderId);

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO updateOrderDescription(int id, String description);

    OrderDTO updateOrderPrice(int id, int stepDisCount);

    OrderDTO updateOrderStatus(int id, TourStatus tourStatus);


}
