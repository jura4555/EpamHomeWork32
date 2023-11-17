package com.epam.spring.travel_agency.controller;

import com.epam.spring.travel_agency.controller.assembler.HotelAssembler;
import com.epam.spring.travel_agency.controller.dto.HotelDTO;
import com.epam.spring.travel_agency.service.HotelService;
import com.epam.spring.travel_agency.test.config.TestConfig;
import com.epam.spring.travel_agency.test.util.TestHotelDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static com.epam.spring.travel_agency.test.util.TestHotelDataUtil.HOTEL_1_ID;
import static com.epam.spring.travel_agency.test.util.TestHotelDataUtil.HOTEL_1_NAME;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HotelController.class)
@Import(TestConfig.class)
public class HotelControllerTest {

    @MockBean
    private HotelService hotelService;

    @SpyBean
    private HotelAssembler hotelAssembler;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllHotelTest() throws Exception {
        HotelDTO hotel1 = TestHotelDataUtil.getHotelDTO1();
        HotelDTO hotel2 = TestHotelDataUtil.getHotelDTO2();
        Pageable pageable = PageRequest.of(0, 2, Sort.by("id").ascending());
        Page<HotelDTO> hotels = new PageImpl<>(List.of(hotel1, hotel2), pageable, 2);
        when(hotelService.getAllHotel(1, 2, "id", "asc")).thenReturn(hotels);

        mockMvc.perform(get("/api/v1/hotel")
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
                        jsonPath("$.content[0].id").value(hotel1.getId()),
                        jsonPath("$.content[0].links").value(hasSize(4)),
                        jsonPath("$.content[1].id").value(hotel2.getId()),
                        jsonPath("$.content[1].links").value(hasSize(4))
                );
        verify(hotelService, times(1)).getAllHotel(1, 2, "id", "asc");
        verify(hotelAssembler, times(1)).toModel(hotel1);
        verify(hotelAssembler, times(1)).toModel(hotel2);
    }

    @Test
    void getHotelByNameTest() throws Exception{
        HotelDTO hotel = TestHotelDataUtil.getHotelDTO1();
        when(hotelService.getHotelByName(HOTEL_1_NAME)).thenReturn(hotel);
        mockMvc.perform(get("/api/v1/hotel/{name}", HOTEL_1_NAME))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(hotel.getId()),
                        jsonPath("$.name").value(hotel.getName()),
                        jsonPath("$.city").value(hotel.getCity()),
                        jsonPath("$.hotelType").value(hotel.getHotelType().name()),
                        jsonPath("$.links").value(hasSize(4))
                );
        verify(hotelService, times(1)).getHotelByName(HOTEL_1_NAME);
        verify(hotelAssembler, times(1)).toModel(hotel);
    }

    @Test
    void createHotelTest() throws Exception{
        ObjectMapper jsonMapper = new ObjectMapper();
        HotelDTO cretedHotel = TestHotelDataUtil.getHotelDTOForCreate();
        HotelDTO hotel = TestHotelDataUtil.getHotelDTO1();
        when(hotelService.createHotel(cretedHotel)).thenReturn(hotel);
        mockMvc.perform(post("/api/v1/hotel")
                        .contentType("application/json")
                        .content(jsonMapper.writeValueAsString(cretedHotel)))
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(hotel.getId()),
                        jsonPath("$.name").value(hotel.getName()),
                        jsonPath("$.city").value(hotel.getCity()),
                        jsonPath("$.hotelType").value(hotel.getHotelType().name()),
                        jsonPath("$.links").value(hasSize(4))
                );
        verify(hotelService, times(1)).createHotel(cretedHotel);
        verify(hotelAssembler, times(1)).toModel(hotel);
    }

    @Test
    void updateHotelTest() throws Exception{
        ObjectMapper jsonMapper = new ObjectMapper();
        HotelDTO updatedHotel = TestHotelDataUtil.getHotelDTOForUpdate();
        HotelDTO hotel = TestHotelDataUtil.getHotelDTOForUpdate();
        hotel.setId(HOTEL_1_ID);
        when(hotelService.updateHotel(HOTEL_1_ID, updatedHotel)).thenReturn(hotel);
        mockMvc.perform(put("/api/v1/hotel/{id}", HOTEL_1_ID)
                        .contentType("application/json")
                        .content(jsonMapper.writeValueAsString(updatedHotel)))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(hotel.getId()),
                        jsonPath("$.name").value(hotel.getName()),
                        jsonPath("$.city").value(hotel.getCity()),
                        jsonPath("$.hotelType").value(hotel.getHotelType().name()),
                        jsonPath("$.links").value(hasSize(4))
                );
        verify(hotelService, times(1)).updateHotel(HOTEL_1_ID, updatedHotel);
        verify(hotelAssembler, times(1)).toModel(hotel);
    }

    @Test
    void deleteHotelTest() throws Exception{
        doNothing().when(hotelService).deleteHotel(HOTEL_1_ID);
        mockMvc.perform(delete("/api/v1/hotel/{id}", HOTEL_1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        verify(hotelService, times(1)).deleteHotel(HOTEL_1_ID);
    }

}
