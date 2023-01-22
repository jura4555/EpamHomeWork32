package com.epam.spring.homework4.controller.assembler;

import com.epam.spring.homework4.controller.TourController;
import com.epam.spring.homework4.controller.dto.TourDTO;
import com.epam.spring.homework4.controller.model.TourModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TourAssembler extends RepresentationModelAssemblerSupport<TourDTO, TourModel> {

    public static final String GET_REL = "get_tour";

    public static final String CREATE_REL = "create_tour";

    public static final String UPDATE_REL = "update_tour";

    public static final String UPDATE_BURNING_REL = "update_tour_burning";

    public static final String UPDATE_MAX_DISCOUNT_REL = "update_tour_max_discount";

    public static final String DELETE_REL = "delete_tour";

    public TourAssembler() {
        super(TourController.class, TourModel.class);
    }

    @Override
    public TourModel toModel(TourDTO entity) {
        TourModel tourModel = new TourModel(entity);
        Link get = linkTo(methodOn(TourController.class).getTourByName(entity.getName()))
                .withRel(GET_REL);
        Link create = linkTo(methodOn(TourController.class).createTour(entity))
                .withRel(CREATE_REL);
        Link update = linkTo(methodOn(TourController.class).updateTour(entity.getId(), entity))
                .withRel(UPDATE_REL);
        Link updateTourBurning = linkTo(methodOn(TourController.class).updateTourBurning(entity.getId(), entity.isBurning()))
                .withRel(UPDATE_BURNING_REL);
        Link updateTourMaxDisCount = linkTo(methodOn(TourController.class).updateTourMaxDisCount(entity.getId(), entity.getMaxDisCount()))
                .withRel(UPDATE_MAX_DISCOUNT_REL);
        Link delete = linkTo(methodOn(TourController.class).deleteTour(entity.getId()))
                .withRel(DELETE_REL);
        tourModel.add(get, create, update, updateTourBurning, updateTourMaxDisCount, delete);
        return tourModel;
    }

    public List<TourModel> toModels(List<TourDTO> entity){
        List<TourModel> tourModels = new ArrayList<>();
        for (int i = 0; i < entity.size(); i++) {
            TourModel tourModel = toModel(entity.get(i));
            tourModels.add(tourModel);
        }
        return tourModels;
    }
}
