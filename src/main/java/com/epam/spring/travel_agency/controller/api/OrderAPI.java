package com.epam.spring.travel_agency.controller.api;

import com.epam.spring.travel_agency.controller.dto.OrderDTO;
import com.epam.spring.travel_agency.controller.dto.validation.group.OnCreate;
import com.epam.spring.travel_agency.controller.model.OrderModel;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Api(tags = "Order management API")
@RequestMapping("api/v1")
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@Validated
public interface OrderAPI {
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", paramType = "query", value = "Page number to be read"),
            @ApiImplicitParam(name = "size", paramType = "query", value = "Page size to be read"),
            @ApiImplicitParam(name = "sortBy", paramType = "query", value = "Field that will be used for sorting"),
            @ApiImplicitParam(name = "order", paramType = "query", value = "Order of sorting")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful get all order")
    })
    @ApiOperation(value = "Get all order", httpMethod = "GET")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order")
    public Page<OrderModel> getAllOrder(@RequestParam(defaultValue = "1")
                                       @Min(value = 1, message = "page should be greater or equals one")
                                       int page,
                                       @RequestParam(defaultValue = "5")
                                       @Min(value = 1, message = "page should be greater or equals one")
                                       int size,
                                       @RequestParam(defaultValue = "id")
                                       @Pattern(regexp = "id",
                                                message = "sortBy should be equal 'id'")
                                       String sortBy,
                                       @RequestParam(defaultValue = "asc")
                                       @Pattern(regexp = "asc|desc",
                                                message = "order should be equal 'asc' or 'desc'")
                                       String order);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "order id by which order are searched")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful get order by id")
    })
    @ApiOperation(value = "Get order by order id", httpMethod = "GET")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order/id/{id}")
    public OrderModel getOrderByOrderId(@PathVariable int id);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "tourStatus", paramType = "path", required = true, value = "tourStatus by which order are searched"),
            @ApiImplicitParam(name = "page", paramType = "query", value = "Page number to be read"),
            @ApiImplicitParam(name = "size", paramType = "query", value = "Page size to be read"),
            @ApiImplicitParam(name = "sortBy", paramType = "query", value = "Field that will be used for sorting"),
            @ApiImplicitParam(name = "order", paramType = "query", value = "Order of sorting")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful get orders by tourStatus")
    })
    @ApiOperation(value = "Get orders by tourStatus", httpMethod = "GET")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order/tour/{tourStatus}")
    public Page<OrderModel> getOrderByTourStatus(@PathVariable TourStatus tourStatus,
                                                @RequestParam(defaultValue = "1")
                                                @Min(value = 1, message = "page should be greater or equals one")
                                                int page,
                                                @RequestParam(defaultValue = "5")
                                                @Min(value = 1, message = "page should be greater or equals one")
                                                int size,
                                                @RequestParam(defaultValue = "id")
                                                @Pattern(regexp = "id",
                                                        message = "sortBy should be equal 'id'")
                                                String sortBy,
                                                @RequestParam(defaultValue = "asc")
                                                @Pattern(regexp = "asc|desc",
                                                            message = "order should be equal 'asc' or 'desc'")
                                                String order);


    @ApiImplicitParams({
            @ApiImplicitParam(   name = "orderDTO",
                    paramType = "body",
                    dataType = "OrderDTO",
                    required = true,
                    value = "information about new order")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 201, message = "Successful create new order")
    })
    @ApiOperation(value = "Create new order", httpMethod = "POST")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/order")
    public OrderModel createOrder(@RequestBody @Validated(OnCreate.class) OrderDTO orderDTO);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "order id identifier"),
            @ApiImplicitParam(name = "description", paramType = "query", required = true, value = "new order description")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful update order description")
    })
    @ApiOperation(value = "Update order description", httpMethod = "PATCH")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/order/{id}/description")
    public OrderModel updateOrderDescription(@PathVariable int id, @RequestParam String description);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "order id identifier"),
            @ApiImplicitParam(name = "stepDisCount", paramType = "query", required = true, value = "new order step discount")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful update order price")
    })
    @ApiOperation(value = "Update order price", httpMethod = "PATCH")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/order/{id}/price")
    public OrderModel updateOrderPrice(@PathVariable int id, @RequestParam @Min(1) @Max(20) int stepDisCount);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "order id identifier"),
            @ApiImplicitParam(name = "tourStatus", paramType = "query", required = true, value = "new order status")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful update order status")
    })
    @ApiOperation(value = "Update order status", httpMethod = "PATCH")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/order/{id}/status")
    public OrderModel updateOrderStatus(@PathVariable int id, @RequestParam TourStatus tourStatus);
}
