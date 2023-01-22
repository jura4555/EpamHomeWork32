package com.epam.spring.homework4.controller.api;

import com.epam.spring.homework4.controller.dto.UserDTO;
import com.epam.spring.homework4.controller.dto.UserDetailsDTO;
import com.epam.spring.homework4.controller.dto.validation.group.OnCreate;
import com.epam.spring.homework4.controller.dto.validation.group.OnUpdate;
import com.epam.spring.homework4.controller.model.UserModel;
import com.epam.spring.homework4.service.model.enums.UserRole;
import com.epam.spring.homework4.service.model.enums.UserStatus;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User management API")
@RequestMapping("api/v1")
@ApiResponses(value = {
        @ApiResponse(code = 500, message = "Internal Server Error")
})
@Validated
public interface UserAPI {

    @ApiImplicitParams({
            @ApiImplicitParam(name = "login", paramType = "path", required = true, value = "user login")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful get user by login")
    })
    @ApiOperation(value = "Get user by login", httpMethod = "GET")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/login/{login}")
    public UserModel getUserByLogin(@PathVariable String login);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "userRole", paramType = "path", required = true, value = "user role")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful get users by role")
    })
    @ApiOperation(value = "Get users by role", httpMethod = "GET")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/role/{userRole}")
    public List<UserModel> getUserByRole(@PathVariable UserRole userRole);


    @ApiImplicitParams({
            @ApiImplicitParam(   name = "userDTO",
                                 paramType = "body",
                                 dataType = "UserDTO",
                                 required = true,
                                 value = "information about new user")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 201, message = "Successful create new user")
    })
    @ApiOperation(value = "Create new user", httpMethod = "POST")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    public UserModel createUser(@RequestBody @Validated(OnCreate.class) UserDTO userDTO);


    @ApiImplicitParams({
            @ApiImplicitParam(   name = "userDTO",
                                 paramType = "body",
                                 dataType = "UserDTO",
                                 required = true,
                                 value = "new information about exist user"),
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "user id identifier")

    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful update main information about user")
    })
    @ApiOperation(value = "Update information about user", httpMethod = "PUT")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping ("/user/{id}")
    public UserModel updateUser(@PathVariable int id, @RequestBody @Validated(OnUpdate.class) UserDTO userDTO);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "user id identifier"),
            @ApiImplicitParam(name = "userRole", paramType = "query", required = true, value = "new role of user")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful update role of user")
    })
    @ApiOperation(value = "Update user role", httpMethod = "PATCH")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping ("/user/{id}/role")
    public UserModel updateUserRole(@PathVariable int id, @RequestParam UserRole userRole);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "user id identifier"),
            @ApiImplicitParam(name = "userStatus", paramType = "query", required = true, value = "new status of user")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful update status of user")
    })
    @ApiOperation(value = "Update user status", httpMethod = "PATCH")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping ("/user/{id}/status")
    public UserModel updateUserStatus(@PathVariable int id, @RequestParam UserStatus userStatus);


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "user id identifier")
    })
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Successful get user details of user")
    })
    @ApiOperation(value = "Get user details", httpMethod = "GET")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{id}/user_details")
    public UserDetailsDTO getUserDetails(@PathVariable int id);
}
