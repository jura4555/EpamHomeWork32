package com.epam.spring.travel_agency.rest_template.controller;

import com.epam.spring.travel_agency.controller.dto.HotelDTO;
import com.epam.spring.travel_agency.rest_template.service.RestTemplateHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/RestTemplate")
@RequiredArgsConstructor
public class RestTemplateHotelController {

    private final RestTemplateHotelService restTemplateHotelService;

    @GetMapping("/hotel")
    public ResponseEntity<List<HotelDTO>> getAllHotel(){
        return restTemplateHotelService.getAllHotel();
    }

    @GetMapping("/hotel/{name}")
    public ResponseEntity<HotelDTO> getHotelByName(@PathVariable String name){
        return restTemplateHotelService.getHotelByName(name);
    }

    @PostMapping("/hotel")
    public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO){
        return restTemplateHotelService.createHotel(hotelDTO);
    }

    @PutMapping("/hotel/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable int id, @RequestBody HotelDTO hotelDTO){
        return restTemplateHotelService.updateHotel(id, hotelDTO);
    }

    @DeleteMapping("/hotel/{id}")
    public void deleteHotel(@PathVariable int id){
        restTemplateHotelService.deleteHotel(id);
    }

}
