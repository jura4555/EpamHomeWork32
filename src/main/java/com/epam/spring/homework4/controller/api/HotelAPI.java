package com.epam.spring.homework4.controller.api;

import com.epam.spring.homework4.controller.dto.HotelDTO;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Hotel management API")
@RequestMapping("api/v1")
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@Validated
public interface HotelAPI {

    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful get all hotel")
    })
    @ApiOperation(value = "Get all hotel", httpMethod = "GET")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hotel")
    public List<HotelDTO> getAllHotel();


    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "path", required = true, value = "hotel name by which hotel are searched")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful get hotel by name")
    })
    @ApiOperation(value = "Get hotel by hotel name", httpMethod = "GET")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hotel/{name}")
    public HotelDTO getHotelByName(@PathVariable String name);


    @ApiImplicitParams({
            @ApiImplicitParam(   name = "hotelDTO",
                    paramType = "body",
                    dataType = "HotelDTO",
                    required = true,
                    value = "information about new hotel")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 201, message = "Successful create new hotel")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/hotel")
    public HotelDTO createHotel(@RequestBody @Valid HotelDTO hotelDTO);


    @ApiImplicitParams({
            @ApiImplicitParam(   name = "hotelDTO",
                    paramType = "body",
                    dataType = "HotelDTO",
                    required = true,
                    value = "new information about exist hotel"),
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "hotel id identifier")

    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful update information about hotel")
    })
    @ApiOperation(value = "Update information about hotel", httpMethod = "PUT")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/hotel/{id}")
    public HotelDTO updateHotel(@PathVariable int id, @RequestBody @Valid HotelDTO hotelDTO);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "hotel id identifier")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 204, message = "Successful delete hotel")
    })
    @ApiOperation(value = "Delete hotel", httpMethod = "DELETE")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/hotel/{id}")
    public boolean deleteHotel(@PathVariable int id);


}
