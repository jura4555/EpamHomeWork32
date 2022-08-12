package com.epam.spring.homework3.service.model.impl;

import com.epam.spring.homework3.service.exception.NotFoundException;
import com.epam.spring.homework3.service.model.Order;
import com.epam.spring.homework3.service.model.enums.TourStatus;
import com.epam.spring.homework3.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
                .toList();
        if(myOrders.isEmpty()){
            throw new NotFoundException("[Repository] Orders is not found!");
        }
        return myOrders;
    }

    @Override
    public Order getOrderByOrderId(int orderId) {
        log.info("[Repository] getOrder by id {} ", orderId);
        return orders.stream()
                .filter(o -> o.getId() == orderId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("[Repository] Order is not found!"));
    }

    @Override
    public Order createOrder(Order order) {
        log.info("[Repository] createOrder " + order);
        id++;
        order.setId(id);
        orders.add(order);
        log.info("[Repository] successfully created order with id:{}", order.getId());
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        log.info("[Repository] updateOrder by all field");
        int orderId = order.getId();
        boolean isDeleted = orders.removeIf(o -> o.getId() == orderId);
        if(isDeleted) {
            orders.add(order);
            log.info("[Repository] updateOrder is done in order: " + order.getId());
        }
        else{
            throw new NotFoundException("[Repository] Order is not found!");
        }
        return order;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        log.info("[Repository] deleteOrder by id {} ", orderId);
        return orders.removeIf(o -> o.getId() == orderId);

    }
}
