package com.epam.spring.homework4.controller.assembler;

import com.epam.spring.homework4.controller.OrderController;
import com.epam.spring.homework4.controller.TourController;
import com.epam.spring.homework4.controller.dto.OrderDTO;
import com.epam.spring.homework4.controller.dto.TourDTO;
import com.epam.spring.homework4.controller.model.OrderModel;
import com.epam.spring.homework4.controller.model.TourModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderAssembler extends RepresentationModelAssemblerSupport<OrderDTO, OrderModel> {

    public static final String GET_REL = "get_order_by_id";

    public static final String CREATE_REL = "create_order";

    public static final String UPDATE_DESCRIPTION_REL = "update_order_description";

    public static final String UPDATE_PRICE_REL = "update_order_price";

    public static final String UPDATE_STATUS_REL = "update_order_status";

    public OrderAssembler() {
        super(OrderController.class, OrderModel.class);
    }


    @Override
    public OrderModel toModel(OrderDTO entity) {
        OrderModel orderModel = new OrderModel(entity);
        Link get = linkTo(methodOn(OrderController.class).getOrderByOrderId(entity.getId()))
                .withRel(GET_REL);
        Link create = linkTo(methodOn(OrderController.class).createOrder(entity))
                .withRel(CREATE_REL);
        Link updateDescription = linkTo(methodOn(OrderController.class).updateOrderDescription(entity.getId(), entity.getDescription()))
                .withRel(UPDATE_DESCRIPTION_REL);
        Link updatePrice = linkTo(methodOn(OrderController.class).updateOrderPrice(entity.getId(), entity.getStepDisCount()))
                .withRel(UPDATE_PRICE_REL);
        Link updateStatus = linkTo(methodOn(OrderController.class).updateOrderStatus(entity.getId(), entity.getTourStatus()))
                .withRel(UPDATE_STATUS_REL);
        orderModel.add(get, create, updateDescription, updatePrice, updateStatus);
        return orderModel;
    }

    public List<OrderModel> toModels(List<OrderDTO> entity){
        List<OrderModel> orderModels = new ArrayList<>();
        for (int i = 0; i < entity.size(); i++) {
            OrderModel orderModel = toModel(entity.get(i));
            orderModels.add(orderModel);
        }
        return orderModels;
    }
}
