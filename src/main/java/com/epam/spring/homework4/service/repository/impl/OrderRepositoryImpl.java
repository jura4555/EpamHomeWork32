package com.epam.spring.homework4.service.repository.impl;

import com.epam.spring.homework4.service.exception.NotFoundException;
import com.epam.spring.homework4.service.exception.OrderNotFoundException;
import com.epam.spring.homework4.service.exception.TourNotFoundException;
import com.epam.spring.homework4.service.model.Order;
import com.epam.spring.homework4.service.model.enums.TourStatus;
import com.epam.spring.homework4.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    private int id;
    private final List<Order> orders = new ArrayList<>();

    @Override
    public List<Order> getAllOrder() {
        log.info("[Repository] successfully received orders");
        return new ArrayList<>(orders);
    }

    @Override
    public List<Order> getOrderByTourStatus(TourStatus tourStatus) {
        log.info("[Repository] getOrders by tourStatus {} ", tourStatus);
        List<Order> myOrders = orders.stream()
                .filter(o -> o.getTourStatus().equals(tourStatus))
                .collect(Collectors.toList());
        if(myOrders.isEmpty()){
            throw new OrderNotFoundException();
        }
        return myOrders;
    }

    @Override
    public Order getOrderByOrderId(int orderId) {
        log.info("[Repository] getOrder by id {} ", orderId);
        return orders.stream()
                .filter(o -> o.getId() == orderId)
                .findFirst()
                .orElseThrow(() -> new OrderNotFoundException());
    }

    @Override
    public Order createOrder(Order order) {
        log.info("[Repository] createOrder " + order);
        id++;
        order.setId(id);
        order.setTourStatus(TourStatus.REGISTERED);
        orders.add(order);
        log.info("[Repository] successfully created order with id:{}", order.getId());
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        log.info("[Repository] updateOrder");
        orders.removeIf(o -> o.getId() == order.getId());
        orders.add(order);
        log.info("[Repository] success updateOrder");
        return order;
    }

}
