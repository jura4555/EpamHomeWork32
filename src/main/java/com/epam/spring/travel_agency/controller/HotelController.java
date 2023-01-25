package com.epam.spring.travel_agency.controller;

import com.epam.spring.travel_agency.controller.api.HotelAPI;
import com.epam.spring.travel_agency.controller.assembler.HotelAssembler;
import com.epam.spring.travel_agency.controller.dto.HotelDTO;
import com.epam.spring.travel_agency.controller.model.HotelModel;
import com.epam.spring.travel_agency.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HotelController implements HotelAPI {

    private final HotelService hotelService;
    private final HotelAssembler hotelAssembler;

    @Override
    public List<HotelModel> getAllHotel() {
        log.info("[Controller] receiving all hotel");
        List<HotelDTO> hotelDTOs = hotelService.getAllHotel();
        return hotelAssembler.toListModel(hotelDTOs);
    }

    @Override
    public HotelModel getHotelByName(String name) {
        log.info("[Controller] getHolel by name {}", name);
        HotelDTO hotelDTO = hotelService.getHotelByName(name);
        return hotelAssembler.toModel(hotelDTO);
    }

    @Override
    public HotelModel createHotel(HotelDTO hotelDTO) {
        log.info("[Controller] createHotel");
        HotelDTO createdHotelDTO = hotelService.createHotel(hotelDTO);
        return hotelAssembler.toModel(createdHotelDTO);
    }

    @Override
    public HotelModel updateHotel(int id, HotelDTO hotelDTO) {
        log.info("[Controller] updateHotel with all fields {}");
        HotelDTO updatedHotelDTO = hotelService.updateHotel(id, hotelDTO);
        return hotelAssembler.toModel(updatedHotelDTO);
    }

    @Override
    public ResponseEntity<Void> deleteHotel(int id) {
        log.info("[Controller] deleteHotel with id {}", id);
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

}
