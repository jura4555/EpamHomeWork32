package com.epam.spring.homework3.service;

import com.epam.spring.homework3.controller.dto.OrderDTO;
import com.epam.spring.homework3.service.model.enums.TourStatus;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrder();

    List<OrderDTO> getOrderByTourStatus(TourStatus tourStatus);

    OrderDTO getOrderByOrderId(int orderId);

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO updateOrder(OrderDTO orderDTO);

    boolean deleteOrder(int orderId);
}
