package com.epam.spring.homework4.service;

import com.epam.spring.homework4.controller.dto.OrderDTO;
import com.epam.spring.homework4.service.model.enums.TourStatus;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrder();

    List<OrderDTO> getOrderByTourStatus(TourStatus tourStatus);

    OrderDTO getOrderByOrderId(int orderId);

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO updateOrderDescription(int id, String description);

    OrderDTO updateOrderPrice(int id, int stepDisCount);

    OrderDTO updateOrderStatus(int id, TourStatus tourStatus);


}
