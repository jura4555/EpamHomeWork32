package com.epam.spring.travel_agency.service.impl;

import com.epam.spring.travel_agency.controller.dto.OrderDTO;
import com.epam.spring.travel_agency.service.OrderService;
import com.epam.spring.travel_agency.service.exception.*;
import com.epam.spring.travel_agency.service.mapper.OrderMapper;
import com.epam.spring.travel_agency.service.model.Order;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;
import com.epam.spring.travel_agency.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Override
    public List<OrderDTO> getAllOrder() {
        log.info("[Service] receiving all orders");
        return orderRepository.findAll().stream()
                .map(orderMapper::mapOrderToOrderDTO)
                .sorted(Comparator.comparing(OrderDTO::getId))
                .collect(Collectors.toList());//+
    }

    @Override
    public List<OrderDTO> getOrderByTourStatus(TourStatus tourStatus) {
        log.info("[Service] getOrders by tourStatus {}", tourStatus);
        List<Order> myOrder = orderRepository.findByTourStatus(tourStatus);
        return myOrder.stream()
                .map(orderMapper::mapOrderToOrderDTO)
                .sorted(Comparator.comparing(OrderDTO::getId))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderByOrderId(int orderId) {
        log.info("[Service] getOrder by id {}", orderId);
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new OrderNotFoundException();
        }
        Order order = optionalOrder.get();
        return orderMapper.mapOrderToOrderDTO(order);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        log.info("[Service] createOrder");
        Order order = orderMapper.mapOrderDTOToOrder(orderDTO);
        order.setTourStatus(TourStatus.REGISTERED);
        order = orderRepository.save(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

    @Override
    public OrderDTO updateOrderDescription(int id, String description) {
        log.info("[Service] updateOrder with description fields");
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()){
            throw new OrderNotFoundException();
        }
        Order order = optionalOrder.get();
        order.setDescription(description);
        order = orderRepository.save(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

    @Override
    public OrderDTO updateOrderPrice(int id, int stepDisCount) {
        log.info("[Service] updateOrder with price fields");
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()){
            throw new OrderNotFoundException();
        }
        Order order = optionalOrder.get();
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
        order = orderRepository.save(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

    @Override
    public OrderDTO updateOrderStatus(int id, TourStatus tourStatus) {
        log.info("[Service] updateOrder with tourStatus fields");
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()){
            throw new OrderNotFoundException();
        }
        Order order = optionalOrder.get();
        if(order.getPrice() == 0){
            throw new PriceNotFoundException();
        }
        if(order.getTourStatus() == TourStatus.CANCELED || order.getTourStatus() == TourStatus.PAID){
            throw new OrderStatusException();
        }
        if(tourStatus == TourStatus.PAID){
            order.getTour().setPlaceCount(order.getTour().getPlaceCount() -1);
        }
        order.setTourStatus(tourStatus);
        order = orderRepository.save(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

}
