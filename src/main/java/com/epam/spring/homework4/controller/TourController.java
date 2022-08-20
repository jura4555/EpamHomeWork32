package com.epam.spring.homework4.controller;

import com.epam.spring.homework4.controller.dto.TourDTO;
import com.epam.spring.homework4.controller.dto.validation.ValidBurning;
import com.epam.spring.homework4.controller.dto.validation.group.OnCreate;
import com.epam.spring.homework4.controller.dto.validation.group.OnUpdate;
import com.epam.spring.homework4.service.TourService;
import com.epam.spring.homework4.service.model.enums.HotelType;
import com.epam.spring.homework4.service.model.enums.TourType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
public class TourController {

    private final TourService tourService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tour")
    public List<TourDTO> getAllTour() {
        log.info("[Controller] receiving all tours");
        return tourService.getAllTour();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tour/type/{tourType}")
    public List<TourDTO> getTourByTourType(@PathVariable TourType tourType) {
        log.info("[Controller] getTours by tourType {}", tourType);
        return tourService.getTourByTourType(tourType);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tour/count/{count}")
    public List<TourDTO> getTourByPlaceCount(@PathVariable int count) {
        log.info("[Controller] getTours by place count {}", count);
        return tourService.getTourByPlaceCount(count);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tour/price/{minPrice}/{maxPrice}")
    public List<TourDTO> getTourByPrice(@PathVariable int minPrice, @PathVariable int maxPrice) {
        log.info("[Controller] getTour by price {} ", minPrice + " < my price < " + maxPrice);
        return tourService.getTourByPrice(minPrice, maxPrice);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tour/hotel/{hotelType}")
    public List<TourDTO> getTourByHotelType(@PathVariable HotelType hotelType) {
        log.info("[Controller] getTours by HotelType {}", hotelType);
        return tourService.getTourByHotelType(hotelType);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/tour")
    public TourDTO createTour(@RequestBody @Validated(OnCreate.class) TourDTO tourDTO) {
        log.info("[Controller] createTour");
        return tourService.createTour(tourDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/tour/{id}")
    public TourDTO updateTour(@PathVariable int id, @RequestBody @Validated(OnUpdate.class) TourDTO tourDTO) {
        log.info("[Controller] updateTour with all fields");
        return tourService.updateTour(id, tourDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/tour/{id}/burning")
    public TourDTO updateTourBurning(@PathVariable int id, @RequestParam boolean burning) {
        log.info("[Controller] updateTour with burning field");
        return tourService.updateTourBurning(id, burning);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/tour/{id}/maxdiscount")
    public TourDTO updateTourMaxDisCount(@PathVariable int id,
                                         @RequestParam @Min(1)
                                                       @Max(70)
                                                       int maxDisCount) {
        log.info("[Controller] updateTour with all fields");
        return tourService.updateTourMaxDisCount(id, maxDisCount);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/tour/{tourId}")
    public boolean deleteTour(@PathVariable(value = "tourId") int id) {
        log.info("[Controller] deleteTour with id {}", id);
        return tourService.deleteTour(id);
    }
}
