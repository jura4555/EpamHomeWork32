package com.epam.spring.travel_agency.service.impl;

import com.epam.spring.travel_agency.controller.dto.OrderDTO;
import com.epam.spring.travel_agency.service.OrderService;
import com.epam.spring.travel_agency.service.exception.MaxDisCountNotFound;
import com.epam.spring.travel_agency.service.exception.PriceNotFoundException;
import com.epam.spring.travel_agency.service.mapper.OrderMapper;
import com.epam.spring.travel_agency.service.model.Order;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;
import com.epam.spring.travel_agency.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
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
        log.info("[Service] createOrder");
        Order order = orderMapper.mapOrderDTOToOrder(orderDTO);
        order = orderRepository.createOrder(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

    @Override
    public OrderDTO updateOrderDescription(int id, String description) {
        log.info("[Service] updateOrder with description fields");
        Order order = orderRepository.getOrderByOrderId(id);
        order.setDescription(description);
        order = orderRepository.updateOrder(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

    @Override
    public OrderDTO updateOrderPrice(int id, int stepDisCount) {
        log.info("[Service] updateOrder with price fields");
        Order order = orderRepository.getOrderByOrderId(id);
        int maxDisCount = order.getTour().getMaxDisCount();
        if(maxDisCount == 0){
            throw new MaxDisCountNotFound();
        }
        Random random = new Random();
        int randNumber = random.nextInt(maxDisCount / stepDisCount) + 1;
        int discount = stepDisCount * randNumber;
        double rawPrice = order.getTour().getPrice();
        double price = rawPrice - (rawPrice * discount/100);
        order.setStepDisCount(stepDisCount);
        order.setDisCount(discount);
        order.setPrice(price);
        order = orderRepository.updateOrder(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

    @Override
    public OrderDTO updateOrderStatus(int id, TourStatus tourStatus) {
        log.info("[Service] updateOrder with tourStatus fields");
        Order order = orderRepository.getOrderByOrderId(id);
        if(order.getPrice() == 0){
            throw new PriceNotFoundException();
        }
        if(tourStatus == TourStatus.PAID){
            order.getTour().setPlaceCount(order.getTour().getPlaceCount() -1);
        }
        order.setTourStatus(tourStatus);
        order = orderRepository.updateOrder(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

}
