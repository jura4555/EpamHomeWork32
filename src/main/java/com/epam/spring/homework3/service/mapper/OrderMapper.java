package com.epam.spring.homework3.service.mapper;

import com.epam.spring.homework3.controller.dto.OrderDTO;
import com.epam.spring.homework3.service.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TourMapper.class, UserMapper.class})
public interface OrderMapper {

    OrderDTO mapOrderToOrderDTO(Order order);

    Order mapOrderDTOToOrder(OrderDTO orderDTO);

}
