package com.epam.spring.homework3.service.impl;

import com.epam.spring.homework3.controller.dto.OrderDTO;
import com.epam.spring.homework3.service.OrderService;
import com.epam.spring.homework3.service.mapper.OrderMapper;
import com.epam.spring.homework3.service.model.Order;
import com.epam.spring.homework3.service.model.enums.TourStatus;
import com.epam.spring.homework3.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Override
    public List<OrderDTO> getAllOrder() {
        log.info("[Service] receiving all orders");
        return orderRepository.getAllOrder().stream()
                .map(orderMapper::mapOrderToOrderDTO)
                .sorted(Comparator.comparing(OrderDTO::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrderByTourStatus(TourStatus tourStatus) {
        log.info("[Service] getOrders by tourStatus {}", tourStatus);
        List<Order> myOrder = orderRepository.getOrderByTourStatus(tourStatus);
        return myOrder.stream()
                .map(orderMapper::mapOrderToOrderDTO)
                .sorted(Comparator.comparing(OrderDTO::getId))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderByOrderId(int orderId) {
        log.info("[Service] getOrder by id {}", orderId);
        Order order = orderRepository.getOrderByOrderId(orderId);
        return orderMapper.mapOrderToOrderDTO(order);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        log.info("[Service] createOrder with id {}", orderDTO.getId());
        Order order = orderMapper.mapOrderDTOToOrder(orderDTO);
        order = orderRepository.createOrder(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        log.info("[Service] updateOrder with all fields");
        Order order = orderMapper.mapOrderDTOToOrder(orderDTO);
        order = orderRepository.updateOrder(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

    @Override
    public boolean deleteOrder(int orderId) {
        log.info("[Service] deleteOrder with id {}", orderId);
        return orderRepository.deleteOrder(orderId);
    }
}
