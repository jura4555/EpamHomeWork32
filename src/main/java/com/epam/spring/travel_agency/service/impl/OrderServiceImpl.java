package com.epam.spring.travel_agency.service.impl;

import com.epam.spring.travel_agency.controller.dto.OrderDTO;
import com.epam.spring.travel_agency.service.OrderService;
import com.epam.spring.travel_agency.service.exception.*;
import com.epam.spring.travel_agency.service.impl.randomizers.MyDisCountRandomizer;
import com.epam.spring.travel_agency.service.mapper.OrderMapper;
import com.epam.spring.travel_agency.service.model.Order;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;
import com.epam.spring.travel_agency.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final MyDisCountRandomizer myDisCountRandomizer;

    @Override
    @Transactional(readOnly = true)
    public Page<OrderDTO> getAllOrder(int page, int size, String sortBy, String order) {
        log.info("[Service] receiving all orders");
        Pageable pageable = PageRequest.of(page - 1, size,
                order.equals("desc") ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending());
        Page<OrderDTO> myOrder = orderRepository.findAll(pageable).map(orderMapper::mapOrderToOrderDTO);
        return myOrder;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderDTO> getOrderByTourStatus(TourStatus tourStatus, int page, int size, String sortBy, String order) {
        log.info("[Service] getOrders by tourStatus {}", tourStatus);
        Pageable pageable = PageRequest.of(page - 1, size,
                order.equals("desc") ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending());
        Page<OrderDTO> myOrder = orderRepository.findByTourStatus(tourStatus, pageable).map(orderMapper::mapOrderToOrderDTO);
        return myOrder;
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
    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        log.info("[Service] createOrder");
        Order order = orderMapper.mapOrderDTOToOrder(orderDTO);
        order.setTourStatus(TourStatus.REGISTERED);
        order = orderRepository.save(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

    @Override
    @Transactional
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
    @Transactional
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
        int discount = myDisCountRandomizer.getRandomDisCount(maxDisCount, stepDisCount);
        double rawPrice = order.getTour().getPrice();
        double price = rawPrice - (rawPrice * discount/100);
        order.setStepDisCount(stepDisCount);
        order.setDisCount(discount);
        order.setPrice(price);
        order = orderRepository.save(order);
        return orderMapper.mapOrderToOrderDTO(order);
    }

    @Override
    @Transactional
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
