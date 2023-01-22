package com.epam.spring.homework4.controller.assembler;

import com.epam.spring.homework4.controller.HotelController;
import com.epam.spring.homework4.controller.dto.HotelDTO;
import com.epam.spring.homework4.controller.model.HotelModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HotelAssembler extends RepresentationModelAssemblerSupport<HotelDTO, HotelModel> {

    public static final String GET_REL = "get_hotel_by_name";

    public static final String CREATE_REL = "create_hotel";

    public static final String UPDATE_REL = "update_hotel";

    public static final String DELETE_REL = "delete_hotel";

    public HotelAssembler() {
        super(HotelController.class, HotelModel.class);
    }

    @Override
    public HotelModel toModel(HotelDTO entity) {
        HotelModel hotelModel = new HotelModel(entity);
        Link get = linkTo(methodOn(HotelController.class).getHotelByName(entity.getName()))
                .withRel(GET_REL);
        Link create = linkTo(methodOn(HotelController.class).createHotel(entity))
                .withRel(CREATE_REL);
        Link update = linkTo(methodOn(HotelController.class).updateHotel(entity.getId(), entity))
                .withRel(UPDATE_REL);
        Link delete = linkTo(methodOn(HotelController.class).deleteHotel(entity.getId()))
                .withRel(DELETE_REL);
        hotelModel.add(get, create, update, delete);
        return hotelModel;
    }

    public List<HotelModel> toListModel(List<HotelDTO> entity){
        List<HotelModel> hotelModels = new ArrayList<>();
        for (int i = 0; i < entity.size(); i++) {
            HotelModel hotelModel = toModel(entity.get(i));
            hotelModels.add(hotelModel);
        }
        return hotelModels;
    }
}















