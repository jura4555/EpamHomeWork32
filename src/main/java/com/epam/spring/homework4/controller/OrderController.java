package com.epam.spring.homework4.controller;

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
@Validated
public class OrderController {

    private final OrderService orderService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order")
    public List<OrderDTO> getAllOrder() {
        log.info("[Controller] receiving all orders");
        return orderService.getAllOrder();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order/id/{id}")
    public OrderDTO getOrderByOrderId(@PathVariable(value = "id") int orderId) {
        log.info("[Controller] getOrder by id {}", orderId);
        return orderService.getOrderByOrderId(orderId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order/tour/{tourStatus}")
    public List<OrderDTO> getOrderByTourStatus(@PathVariable TourStatus tourStatus) {
        log.info("[Controller] getOrders by tourStatus {}", tourStatus);
        return orderService.getOrderByTourStatus(tourStatus);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/order")
    public OrderDTO createOrder(@RequestBody @Validated(OnCreate.class) OrderDTO orderDTO) {
        log.info("[Controller] createOrder {}");
        return orderService.createOrder(orderDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/order/{id}/description")
    public OrderDTO updateOrderDescription(@PathVariable int id, @RequestParam String description) {
        log.info("[Controller] updateOrder with description fields");
        return orderService.updateOrderDescription(id, description);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/order/{id}/price")
    public OrderDTO updateOrderPrice(@PathVariable int id, @RequestParam @Min(1)
                                                                         @Max(20) int stepDisCount) {
        log.info("[Controller] updateOrder with price fields");
        return orderService.updateOrderPrice(id, stepDisCount);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/order/{id}/status")
    public OrderDTO updateOrderStatus(@PathVariable int id, @RequestParam TourStatus tourStatus) {
        log.info("[Controller] updateOrder with tourStatus field");
        return orderService.updateOrderStatus(id, tourStatus);
    }

}
