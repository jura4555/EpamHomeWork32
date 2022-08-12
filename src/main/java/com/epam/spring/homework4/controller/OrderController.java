package com.epam.spring.homework4.controller;

import com.epam.spring.homework4.controller.dto.OrderDTO;
import com.epam.spring.homework4.service.OrderService;
import com.epam.spring.homework4.service.model.enums.TourStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order")
    public List<OrderDTO> getAllOrder() {
        log.info("[Controller] receiving all orders");
        return orderService.getAllOrder();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order/tour/{tourStatus}")
    public List<OrderDTO> getOrderByTourStatus(@PathVariable TourStatus tourStatus) {
        log.info("[Controller] getOrders by tourStatus {}", tourStatus);
        return orderService.getOrderByTourStatus(tourStatus);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order/id/{id}")
    public OrderDTO getOrderByOrderId(@PathVariable(value = "id") int orderId) {
        log.info("[Controller] getOrder by id {}", orderId);
        return orderService.getOrderByOrderId(orderId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/order")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        log.info("[Controller] createOrder with id {}", orderDTO.getId());
        return orderService.createOrder(orderDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/order")
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO) {
        log.info("[Controller] updateOrder with all fields");
        return orderService.updateOrder(orderDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/order/{id}")
    public boolean deleteOrder(@PathVariable(value = "id") int orderId) {
        log.info("[Controller] deleteOrder with id {}", orderId);
        return orderService.deleteOrder(orderId);
    }

}
