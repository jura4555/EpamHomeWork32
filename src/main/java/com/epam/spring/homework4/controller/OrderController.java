package com.epam.spring.homework4.controller;

import com.epam.spring.homework4.controller.api.OrderAPI;
import com.epam.spring.homework4.controller.dto.OrderDTO;
import com.epam.spring.homework4.controller.dto.validation.group.OnCreate;
import com.epam.spring.homework4.service.OrderService;
import com.epam.spring.homework4.service.model.enums.TourStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController implements OrderAPI{

    private final OrderService orderService;

    @Override
    public List<OrderDTO> getAllOrder() {
        log.info("[Controller] receiving all orders");
        return orderService.getAllOrder();
    }

    @Override
    public OrderDTO getOrderByOrderId(int id) {
        log.info("[Controller] getOrder by id {}", id);
        return orderService.getOrderByOrderId(id);
    }

    @Override
    public List<OrderDTO> getOrderByTourStatus(TourStatus tourStatus) {
        log.info("[Controller] getOrders by tourStatus {}", tourStatus);
        return orderService.getOrderByTourStatus(tourStatus);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        log.info("[Controller] createOrder {}");
        return orderService.createOrder(orderDTO);
    }

    @Override
    public OrderDTO updateOrderDescription(int id, String description) {
        log.info("[Controller] updateOrder with description fields");
        return orderService.updateOrderDescription(id, description);
    }

    @Override
    public OrderDTO updateOrderPrice(int id, int stepDisCount) {
        log.info("[Controller] updateOrder with price fields");
        return orderService.updateOrderPrice(id, stepDisCount);
    }

    @Override
    public OrderDTO updateOrderStatus(int id, TourStatus tourStatus) {
        log.info("[Controller] updateOrder with tourStatus field");
        return orderService.updateOrderStatus(id, tourStatus);
    }

}
