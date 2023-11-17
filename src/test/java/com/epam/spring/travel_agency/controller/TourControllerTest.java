package com.epam.spring.travel_agency.controller;


import com.epam.spring.travel_agency.controller.assembler.TourAssembler;
import com.epam.spring.travel_agency.controller.dto.TourDTO;
import com.epam.spring.travel_agency.service.TourService;
import com.epam.spring.travel_agency.service.model.enums.HotelType;
import com.epam.spring.travel_agency.service.model.enums.TourType;
import com.epam.spring.travel_agency.test.config.TestConfig;
import com.epam.spring.travel_agency.test.util.TestTourDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.epam.spring.travel_agency.test.util.TestTourDataUtil.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TourController.class)
@Import(TestConfig.class)
public class TourControllerTest {

    @MockBean
    private TourService tourService;

    @SpyBean
    private TourAssembler tourAssembler;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllTourTest() throws Exception{
        TourDTO tour1 = TestTourDataUtil.getTourDTO1();
        TourDTO tour2 = TestTourDataUtil.getTourDTO2();
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<TourDTO> tours =  new PageImpl<>(List.of(tour1, tour2), pageable, 2);
        when(tourService.getAllTour(1, 2, "id", "asc")).thenReturn(tours);
        mockMvc.perform(get("/api/v1/tour")
                        .queryParam("page", String.valueOf(1))
                        .queryParam("size", String.valueOf(2))
                        .queryParam("sortBy", "id")
                        .queryParam("order", "asc"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.pageable.pageNumber").value(0),
                        jsonPath("$.pageable.pageSize").value(2),
                        jsonPath("$.content[0].id").value(tour1.getId()),
                        jsonPath("$.content[0].links").value(hasSize(6)),
                        jsonPath("$.content[1].id").value(tour2.getId()),
                        jsonPath("$.content[1].links").value(hasSize(6))
                );
        verify(tourService, times(1)).getAllTour(1, 2, "id", "asc");
        verify(tourAssembler, times(1)).toModel(tour1);
        verify(tourAssembler, times(1)).toModel(tour2);
    }

    @Test
    void getTourByNameTest() throws Exception{
        TourDTO tour = TestTourDataUtil.getTourDTO1();
        when(tourService.getTourByName(tour.getName())).thenReturn(tour);
        mockMvc.perform(get("/api/v1/tour/name/{name}", tour.getName()))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(tour.getId()),
                        jsonPath("$.name").value(tour.getName()),
                        jsonPath("$.price").value(tour.getPrice()),
                        jsonPath("$.dateDaparture").value(tour.getDateDaparture().toString()),
                        jsonPath("$.dateArrival").value(tour.getDateArrival().toString()),
                        jsonPath("$.placeDaparture").value(tour.getPlaceDaparture()),
                        jsonPath("$.placeArrival").value(tour.getPlaceArrival()),
                        jsonPath("$.maxDisCount").value(tour.getMaxDisCount()),
                        jsonPath("$.placeCount").value(tour.getPlaceCount()),
                        jsonPath("$.tourType").value(tour.getTourType().name()),
                        jsonPath("$.burning").value(tour.isBurning()),
                        jsonPath("$.hotel.id").value(tour.getHotel().getId()),
                        jsonPath("$.links").value(hasSize(6))
                );
        verify(tourService, times(1)).getTourByName(tour.getName());
        verify(tourAssembler, times(1)).toModel(tour);
    }

    @Test
    void getTourByTourTypeTest() throws Exception{
        TourDTO tour1 = TestTourDataUtil.getTourDTO1();
        TourDTO tour2 = TestTourDataUtil.getTourDTO2();
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<TourDTO> tours =  new PageImpl<>(List.of(tour1, tour2), pageable, 2);
        when(tourService.getTourByTourType(TourType.REST, 1, 2, "id", "asc")).thenReturn(tours);
        mockMvc.perform(get("/api/v1/tour/type/{tourType}", TourType.REST)
                        .queryParam("page", String.valueOf(1))
                        .queryParam("size", String.valueOf(2))
                        .queryParam("sortBy", "id")
                        .queryParam("order", "asc"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.pageable.pageNumber").value(0),
                        jsonPath("$.pageable.pageSize").value(2),
                        jsonPath("$.content[0].id").value(tour1.getId()),
                        jsonPath("$.content[0].links").value(hasSize(6)),
                        jsonPath("$.content[1].id").value(tour2.getId()),
                        jsonPath("$.content[1].links").value(hasSize(6))
                );
        verify(tourService, times(1)).getTourByTourType(TourType.REST, 1, 2, "id", "asc");
        verify(tourAssembler, times(1)).toModel(tour1);
        verify(tourAssembler, times(1)).toModel(tour2);
    }

    @Test
    void getTourByPlaceCountTest() throws Exception{
        TourDTO tour1 = TestTourDataUtil.getTourDTO1();
        Pageable pageable = PageRequest.of(0, 1, Sort.by("id").ascending());
        Page<TourDTO> tours =  new PageImpl<>(List.of(tour1), pageable, 1);
        when(tourService.getTourByPlaceCount(5, 1, 1, "id", "asc")).thenReturn(tours);
        mockMvc.perform(get("/api/v1/tour/count/{count}", 5)
                        .queryParam("page", String.valueOf(1))
                        .queryParam("size", String.valueOf(1))
                        .queryParam("sortBy", "id")
                        .queryParam("order", "asc"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.pageable.pageNumber").value(0),
                        jsonPath("$.pageable.pageSize").value(1),
                        jsonPath("$.content[0].id").value(tour1.getId()),
                        jsonPath("$.content[0].links").value(hasSize(6))
                );
        verify(tourService, times(1)).getTourByPlaceCount(5, 1, 1, "id", "asc");
        verify(tourAssembler, times(1)).toModel(tour1);
    }

    @Test
    void getTourByPriceTest() throws Exception{
        double minPrice = 2000;
        double maxPrice = 4000;
        TourDTO tour1 = TestTourDataUtil.getTourDTO1();
        TourDTO tour2 = TestTourDataUtil.getTourDTO2();
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<TourDTO> tours =  new PageImpl<>(List.of(tour1, tour2), pageable, 2);
        when(tourService.getTourByPrice(minPrice, maxPrice, 1, 2, "id", "asc")).thenReturn(tours);
        mockMvc.perform(get("/api/v1/tour/price/{minPrice}/{maxPrice}", minPrice, maxPrice)
                        .queryParam("page", String.valueOf(1))
                        .queryParam("size", String.valueOf(2))
                        .queryParam("sortBy", "id")
                        .queryParam("order", "asc"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.pageable.pageNumber").value(0),
                        jsonPath("$.pageable.pageSize").value(2),
                        jsonPath("$.content[0].id").value(tour1.getId()),
                        jsonPath("$.content[0].links").value(hasSize(6)),
                        jsonPath("$.content[1].id").value(tour2.getId()),
                        jsonPath("$.content[1].links").value(hasSize(6))
                );
        verify(tourService, times(1)).getTourByPrice(minPrice, maxPrice, 1, 2, "id", "asc");
        verify(tourAssembler, times(1)).toModel(tour1);
        verify(tourAssembler, times(1)).toModel(tour2);
    }

    @Test
    void getTourByHotelTypeTest() throws Exception{
        TourDTO tour1 = TestTourDataUtil.getTourDTO1();
        TourDTO tour2 = TestTourDataUtil.getTourDTO2();
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<TourDTO> tours =  new PageImpl<>(List.of(tour1, tour2), pageable, 2);
        when(tourService.getTourByHotelType(HotelType.TOURIST_CLASS, 1, 2, "id", "asc")).thenReturn(tours);
        mockMvc.perform(get("/api/v1/tour/hotel/{hotelType}", HotelType.TOURIST_CLASS)
                        .queryParam("page", String.valueOf(1))
                        .queryParam("size", String.valueOf(2))
                        .queryParam("sortBy", "id")
                        .queryParam("order", "asc"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.pageable.pageNumber").value(0),
                        jsonPath("$.pageable.pageSize").value(2),
                        jsonPath("$.content[0].id").value(tour1.getId()),
                        jsonPath("$.content[0].links").value(hasSize(6)),
                        jsonPath("$.content[1].id").value(tour2.getId()),
                        jsonPath("$.content[1].links").value(hasSize(6))
                );
        verify(tourService, times(1)).getTourByHotelType(HotelType.TOURIST_CLASS, 1, 2, "id", "asc");
        verify(tourAssembler, times(1)).toModel(tour1);
        verify(tourAssembler, times(1)).toModel(tour2);
    }

    @Test
    void createTourTest() throws Exception{
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        TourDTO createdTour = TestTourDataUtil.getTourDTOForCreate();
        TourDTO tour = TestTourDataUtil.getTourDTO1();
        when(tourService.createTour(createdTour)).thenReturn(tour);
        mockMvc.perform(post("/api/v1/tour")
                        .contentType("application/json")
                        .content(jsonMapper.writeValueAsString(createdTour)))
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(tour.getId()),
                        jsonPath("$.name").value(tour.getName()),
                        jsonPath("$.price").value(tour.getPrice()),
                        jsonPath("$.dateDaparture").value(tour.getDateDaparture().toString()),
                        jsonPath("$.dateArrival").value(tour.getDateArrival().toString()),
                        jsonPath("$.placeDaparture").value(tour.getPlaceDaparture()),
                        jsonPath("$.placeArrival").value(tour.getPlaceArrival()),
                        jsonPath("$.maxDisCount").value(tour.getMaxDisCount()),
                        jsonPath("$.placeCount").value(tour.getPlaceCount()),
                        jsonPath("$.tourType").value(tour.getTourType().name()),
                        jsonPath("$.burning").value(tour.isBurning()),
                        jsonPath("$.hotel.id").value(tour.getHotel().getId()),
                        jsonPath("$.links").value(hasSize(6))
                );
        verify(tourService, times(1)).createTour(createdTour);
        verify(tourAssembler, times(1)).toModel(tour);
    }

    @Test
    void updateTourTest() throws Exception{
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        TourDTO tour = TestTourDataUtil.getTourDTOForUpdate();
        tour.setId(TOUR_1_ID);
        tour.setPlaceCount(TOUR_1_PLACE_COUNT);
        tour.setBurning(TOUR_1_BURNING);
        when(tourService.updateTour(TOUR_1_ID, TestTourDataUtil.getTourDTOForUpdate())).thenReturn(tour);
        mockMvc.perform(put("/api/v1/tour/{id}", TOUR_1_ID)
                        .contentType("application/json")
                        .content(jsonMapper.writeValueAsString(TestTourDataUtil.getTourDTOForUpdate())))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(tour.getId()),
                        jsonPath("$.name").value(tour.getName()),
                        jsonPath("$.price").value(tour.getPrice()),
                        jsonPath("$.dateDaparture").value(tour.getDateDaparture().toString()),
                        jsonPath("$.dateArrival").value(tour.getDateArrival().toString()),
                        jsonPath("$.placeDaparture").value(tour.getPlaceDaparture()),
                        jsonPath("$.placeArrival").value(tour.getPlaceArrival()),
                        jsonPath("$.maxDisCount").value(tour.getMaxDisCount()),
                        jsonPath("$.placeCount").value(tour.getPlaceCount()),
                        jsonPath("$.tourType").value(tour.getTourType().name()),
                        jsonPath("$.burning").value(tour.isBurning()),
                        jsonPath("$.hotel.id").value(tour.getHotel().getId()),
                        jsonPath("$.links").value(hasSize(6))
                );
        verify(tourService, times(1)).updateTour(tour.getId(), TestTourDataUtil.getTourDTOForUpdate());
        verify(tourAssembler, times(1)).toModel(tour);
    }

    @Test
    void updateTourBurningTest() throws Exception{
        boolean newBurning = false;
        TourDTO tour = TestTourDataUtil.getTourDTO1();
        tour.setBurning(newBurning);
        when(tourService.updateTourBurning(tour.getId(), newBurning)).thenReturn(tour);
        mockMvc.perform(patch("/api/v1/tour/{id}/burning", TOUR_1_ID)
                        .queryParam("burning", String.valueOf(newBurning))
                )
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(tour.getId()),
                        jsonPath("$.name").value(tour.getName()),
                        jsonPath("$.price").value(tour.getPrice()),
                        jsonPath("$.dateDaparture").value(tour.getDateDaparture().toString()),
                        jsonPath("$.dateArrival").value(tour.getDateArrival().toString()),
                        jsonPath("$.placeDaparture").value(tour.getPlaceDaparture()),
                        jsonPath("$.placeArrival").value(tour.getPlaceArrival()),
                        jsonPath("$.maxDisCount").value(tour.getMaxDisCount()),
                        jsonPath("$.placeCount").value(tour.getPlaceCount()),
                        jsonPath("$.tourType").value(tour.getTourType().name()),
                        jsonPath("$.burning").value(tour.isBurning()),
                        jsonPath("$.hotel.id").value(tour.getHotel().getId()),
                        jsonPath("$.links").value(hasSize(6))
                );
        verify(tourService, times(1)).updateTourBurning(tour.getId(), newBurning);
        verify(tourAssembler, times(1)).toModel(tour);
    }

    @Test
    void updateTourMaxDisCountTest() throws Exception{
        int newMaxDixCount = 20;
        TourDTO tour = TestTourDataUtil.getTourDTO1();
        tour.setMaxDisCount(newMaxDixCount);
        when(tourService.updateTourMaxDisCount(tour.getId(), newMaxDixCount)).thenReturn(tour);
        mockMvc.perform(patch("/api/v1/tour/{id}/maxdiscount", TOUR_1_ID)
                        .queryParam("maxDisCount", String.valueOf(newMaxDixCount))
                )
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(tour.getId()),
                        jsonPath("$.name").value(tour.getName()),
                        jsonPath("$.price").value(tour.getPrice()),
                        jsonPath("$.dateDaparture").value(tour.getDateDaparture().toString()),
                        jsonPath("$.dateArrival").value(tour.getDateArrival().toString()),
                        jsonPath("$.placeDaparture").value(tour.getPlaceDaparture()),
                        jsonPath("$.placeArrival").value(tour.getPlaceArrival()),
                        jsonPath("$.maxDisCount").value(tour.getMaxDisCount()),
                        jsonPath("$.placeCount").value(tour.getPlaceCount()),
                        jsonPath("$.tourType").value(tour.getTourType().name()),
                        jsonPath("$.burning").value(tour.isBurning()),
                        jsonPath("$.hotel.id").value(tour.getHotel().getId()),
                        jsonPath("$.links").value(hasSize(6))
                );
        verify(tourService, times(1)).updateTourMaxDisCount(tour.getId(), newMaxDixCount);
        verify(tourAssembler, times(1)).toModel(tour);
    }

    @Test
    void deleteTourTest() throws Exception{
        doNothing().when(tourService).deleteTour(TOUR_1_ID);
        mockMvc.perform(delete("/api/v1/tour/{id}", TOUR_1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(tourService, times(1)).deleteTour(TOUR_1_ID);
    }
}
