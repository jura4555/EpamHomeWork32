package com.epam.spring.travel_agency.controller;

import com.epam.spring.travel_agency.controller.api.OrderAPI;
import com.epam.spring.travel_agency.controller.assembler.OrderAssembler;
import com.epam.spring.travel_agency.controller.dto.OrderDTO;
import com.epam.spring.travel_agency.controller.model.OrderModel;
import com.epam.spring.travel_agency.service.OrderService;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController implements OrderAPI {

    private final OrderService orderService;
    private final OrderAssembler orderAssembler;

    @Override
    public List<OrderModel> getAllOrder() {
        log.info("[Controller] receiving all orders");
        List<OrderDTO> orderDTOs = orderService.getAllOrder();
        return orderAssembler.toModels(orderDTOs);
    }

    @Override
    public OrderModel getOrderByOrderId(int id) {
        log.info("[Controller] getOrder by id {}", id);
        OrderDTO orderDTO = orderService.getOrderByOrderId(id);
        return orderAssembler.toModel(orderDTO);
    }

    @Override
    public List<OrderModel> getOrderByTourStatus(TourStatus tourStatus) {
        log.info("[Controller] getOrders by tourStatus {}", tourStatus);
        List<OrderDTO> orderDTOs = orderService.getOrderByTourStatus(tourStatus);
        return orderAssembler.toModels(orderDTOs);
    }

    @Override
    public OrderModel createOrder(OrderDTO orderDTO) {
        log.info("[Controller] createOrder {}");
        OrderDTO createdOrderDTO = orderService.createOrder(orderDTO);
        return orderAssembler.toModel(createdOrderDTO);
    }

    @Override
    public OrderModel updateOrderDescription(int id, String description) {
        log.info("[Controller] updateOrder with description fields");
        OrderDTO updatedOrderDTO = orderService.updateOrderDescription(id, description);
        return orderAssembler.toModel(updatedOrderDTO);
    }

    @Override
    public OrderModel updateOrderPrice(int id, int stepDisCount) {
        log.info("[Controller] updateOrder with price fields");
        OrderDTO updatedOrderDTO = orderService.updateOrderPrice(id, stepDisCount);
        return orderAssembler.toModel(updatedOrderDTO);
    }

    @Override
    public OrderModel updateOrderStatus(int id, TourStatus tourStatus) {
        log.info("[Controller] updateOrder with tourStatus field");
        OrderDTO updatedOrderDTO = orderService.updateOrderStatus(id, tourStatus);
        return orderAssembler.toModel(updatedOrderDTO);
    }

}
