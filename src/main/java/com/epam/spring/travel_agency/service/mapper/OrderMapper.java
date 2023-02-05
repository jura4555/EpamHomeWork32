package com.epam.spring.travel_agency.service.mapper;

import com.epam.spring.travel_agency.controller.dto.OrderDTO;
import com.epam.spring.travel_agency.service.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TourMapper.class, UserMapper.class})
public interface OrderMapper {

    OrderDTO mapOrderToOrderDTO(Order order);

    Order mapOrderDTOToOrder(OrderDTO orderDTO);

}
