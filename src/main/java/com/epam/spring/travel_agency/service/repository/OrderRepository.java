package com.epam.spring.travel_agency.service.repository;

import com.epam.spring.travel_agency.service.model.Order;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;

import java.util.List;

public interface OrderRepository {

    List<Order> getAllOrder();

    List<Order> getOrderByTourStatus(TourStatus tourStatus);

    Order getOrderByOrderId(int orderId);

    Order createOrder(Order order);

    Order updateOrder(Order order);


}
