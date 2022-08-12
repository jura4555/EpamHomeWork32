package com.epam.spring.homework4.service.repository;

import com.epam.spring.homework4.service.model.Order;
import com.epam.spring.homework4.service.model.enums.TourStatus;

import java.util.List;

public interface OrderRepository {

    List<Order> getAllOrder();

    List<Order> getOrderByTourStatus(TourStatus tourStatus);

    Order getOrderByOrderId(int orderId);

    Order createOrder(Order order);

    Order updateOrder(Order order);

    boolean deleteOrder(int orderId);

}
