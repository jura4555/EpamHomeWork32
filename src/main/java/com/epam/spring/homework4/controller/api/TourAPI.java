package com.epam.spring.homework4.controller.api;

import com.epam.spring.homework4.controller.dto.TourDTO;
import com.epam.spring.homework4.controller.dto.validation.group.OnCreate;
import com.epam.spring.homework4.controller.dto.validation.group.OnUpdate;
import com.epam.spring.homework4.service.model.enums.HotelType;
import com.epam.spring.homework4.service.model.enums.TourType;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;

@Api(tags = "Tour management API")
@RequestMapping("api/v1")
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@Validated
public interface TourAPI {

    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful get all tour")
    })
    @ApiOperation(value = "Get all tour", httpMethod = "GET")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tour")
    public List<TourDTO> getAllTour();


    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "path", required = true, value = "tour name by which tour are searched")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful get tour by name")
    })
    @ApiOperation(value = "Get tour by tour name", httpMethod = "GET")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tour/name/{name}")
    public TourDTO getTourByName(@PathVariable String name);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "tourType", paramType = "path", required = true, value = "tour type by which tours are searched")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful get tours by tour type")
    })
    @ApiOperation(value = "Get tours by tour type", httpMethod = "GET")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tour/type/{tourType}")
    public List<TourDTO> getTourByTourType(@PathVariable TourType tourType);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "count", paramType = "path", required = true, value = "place count by which tours are searched")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful get tours by place count")
    })
    @ApiOperation(value = "Get tours by place count", httpMethod = "GET")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tour/count/{count}")
    public List<TourDTO> getTourByPlaceCount(@PathVariable int count);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "minPrice", paramType = "path", required = true, value = "lower price range"),
            @ApiImplicitParam(name = "maxPrice", paramType = "path", required = true, value = "upper price range")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful get tours by range price")
    })
    @ApiOperation(value = "Get tours by range price", httpMethod = "GET")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tour/price/{minPrice}/{maxPrice}")
    public List<TourDTO> getTourByPrice(@PathVariable @Positive int minPrice, @PathVariable @Positive int maxPrice);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "hotelType", paramType = "path", required = true, value = "the type of hotel by which tours are searched")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful get tours by hotel type")
    })
    @ApiOperation(value = "Get tours by hotel type", httpMethod = "GET")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tour/hotel/{hotelType}")
    public List<TourDTO> getTourByHotelType(@PathVariable HotelType hotelType);


    @ApiImplicitParams({
            @ApiImplicitParam(   name = "tourDTO",
                    paramType = "body",
                    dataType = "TourDTO",
                    required = true,
                    value = "information about new tour")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 201, message = "Successful create new tour")
    })
    @ApiOperation(value = "Create new tour", httpMethod = "POST")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/tour")
    public TourDTO createTour(@RequestBody @Validated(OnCreate.class) TourDTO tourDTO);


    @ApiImplicitParams({
            @ApiImplicitParam(   name = "tourDTO",
                    paramType = "body",
                    dataType = "TourDTO",
                    required = true,
                    value = "new information about exist tour"),
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "tour id identifier")

    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful update main information about tour")
    })
    @ApiOperation(value = "Update information about tour", httpMethod = "PUT")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/tour/{id}")
    public TourDTO updateTour(@PathVariable int id, @RequestBody @Validated(OnUpdate.class) TourDTO tourDTO);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "tour id identifier"),
            @ApiImplicitParam(name = "burning", paramType = "query", required = true, value = "new value of burning field in tour")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful update tour field burning in tour")
    })
    @ApiOperation(value = "Update tour burning(field)", httpMethod = "PATCH")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/tour/{id}/burning")
    public TourDTO updateTourBurning(@PathVariable int id, @RequestParam boolean burning);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "tour id identifier"),
            @ApiImplicitParam(name = "maxDisCount", paramType = "query", required = true, value = "maximum discount")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful set the maximum discount for the tour")
    })
    @ApiOperation(value = "Set the maximum discount for the tour", httpMethod = "PATCH")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/tour/{id}/maxdiscount")
    public TourDTO updateTourMaxDisCount(@PathVariable int id, @RequestParam @Min(1) @Max(70) int maxDisCount);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "tour id identifier")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 204, message = "Successful delete tour")
    })
    @ApiOperation(value = "Delete tour", httpMethod = "DELETE")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/tour/{id}")
    public boolean deleteTour(@PathVariable int id);
}
