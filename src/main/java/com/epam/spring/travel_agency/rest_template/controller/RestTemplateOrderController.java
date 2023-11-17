package com.epam.spring.travel_agency.rest_template.controller;

import com.epam.spring.travel_agency.controller.dto.OrderDTO;
import com.epam.spring.travel_agency.rest_template.service.RestTemplateOrderService;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RestTemplate")
@RequiredArgsConstructor
public class RestTemplateOrderController {

    private final RestTemplateOrderService restTemplateOrderService;

    @GetMapping("/order")
    public ResponseEntity<List<OrderDTO>> getAllOrder(){
        return restTemplateOrderService.getAllOrder();
    }

    @GetMapping("/order/id/{id}")
    public ResponseEntity<OrderDTO> getOrderByOrderId(@PathVariable int id){
        return restTemplateOrderService.getOrderByOrderId(id);
    }

    @GetMapping("/order/tour/{tourStatus}")
    public ResponseEntity<List<OrderDTO>> getOrderByTourStatus(@PathVariable TourStatus tourStatus){
        return restTemplateOrderService.getOrderByTourStatus(tourStatus);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO){
        return restTemplateOrderService.createOrder(orderDTO);
    }

    @PatchMapping("/order/{id}/description")
    public ResponseEntity<OrderDTO> updateOrderDescription(@PathVariable int id, @RequestParam String description){
        return restTemplateOrderService.updateOrderDescription(id, description);
    }

    @PatchMapping("/order/{id}/price")
    public ResponseEntity<OrderDTO> updateOrderPrice(@PathVariable int id, @RequestParam int stepDisCount){
        return restTemplateOrderService.updateOrderPrice(id, stepDisCount);
    }

    @PatchMapping("/order/{id}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable int id, @RequestParam TourStatus tourStatus){
        return restTemplateOrderService.updateOrderStatus(id, tourStatus);
    }
}
