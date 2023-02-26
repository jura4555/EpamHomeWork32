package com.epam.spring.travel_agency.rest_template.controller;

import com.epam.spring.travel_agency.controller.dto.TourDTO;
import com.epam.spring.travel_agency.rest_template.service.RestTemplateTourService;
import com.epam.spring.travel_agency.service.model.enums.HotelType;
import com.epam.spring.travel_agency.service.model.enums.TourType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RestTemplate")
@RequiredArgsConstructor
public class RestTemplteTourController {

    private final RestTemplateTourService restTemplateTourService;

    @GetMapping("/tour")
    public ResponseEntity<List<TourDTO>> getAllTour(){
        return restTemplateTourService.getAllTour();
    }

    @GetMapping("/tour/name/{name}")
    public ResponseEntity<TourDTO> getTourByName(@PathVariable String name){
        return restTemplateTourService.getTourByName(name);
    }

    @GetMapping("/tour/type/{tourType}")
    public ResponseEntity<List<TourDTO>> getTourByTourType(@PathVariable TourType tourType){
        return restTemplateTourService.getTourByTourType(tourType);
    }

    @GetMapping("/tour/count/{count}")
    public ResponseEntity<List<TourDTO>> getTourByPlaceCount(@PathVariable int count){
        return restTemplateTourService.getTourByPlaceCount(count);
    }

    @GetMapping("/tour/price/{minPrice}/{maxPrice}")
    public ResponseEntity<List<TourDTO>> getTourByPrice(@PathVariable int minPrice, @PathVariable int maxPrice){
        return restTemplateTourService.getTourByPrice(minPrice, maxPrice);
    }

    @GetMapping("/tour/hotel/{hotelType}")
    public ResponseEntity<List<TourDTO>> getTourByHotelType(@PathVariable HotelType hotelType){
        return restTemplateTourService.getTourByHotelType(hotelType);
    }

    @PostMapping("/tour")
    public ResponseEntity<TourDTO> createTour(@RequestBody TourDTO tourDTO){
        return restTemplateTourService.createTour(tourDTO);
    }

    @PutMapping("/tour/{id}")
    public ResponseEntity<TourDTO> updateTour(@PathVariable int id, @RequestBody TourDTO tourDTO){
        return restTemplateTourService.updateTour(id, tourDTO);
    }

    @PatchMapping("/tour/{id}/burning")
    public ResponseEntity<TourDTO> updateTourBurning(@PathVariable int id, @RequestParam boolean burning){
        return restTemplateTourService.updateTourBurning(id, burning);
    }

    @PatchMapping("/tour/{id}/maxdiscount")
    public ResponseEntity<TourDTO> updateTourMaxDisCount(@PathVariable int id, @RequestParam int maxDisCount){
        return restTemplateTourService.updateTourMaxDisCount(id, maxDisCount);
    }

    @DeleteMapping("/tour/{id}")
    public void deleteTour(@PathVariable int id){
        restTemplateTourService.deleteTour(id);
    }
}
