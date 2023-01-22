package com.epam.spring.homework4.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.spring.homework4.controller.UserController;
import com.epam.spring.homework4.controller.dto.UserDTO;
import com.epam.spring.homework4.controller.model.UserModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDTO, UserModel> {

    public static final String GET_REL = "get_user_by_login";
    public static final String CREATE_REL = "create_user";
    public static final String UPDATE_REL = "update_user";
    public static final String UPDATE_ROLE_REL = "update_user_role";
    public static final String UPDATE_STATUS_REL = "update_user_status";

    public UserAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(UserDTO entity) {
        UserModel userModel = new UserModel(entity);
        Link getUserByLogin = linkTo(methodOn(UserController.class).getUserByLogin(entity.getLogin()))
                .withRel(GET_REL);
        Link create = linkTo(methodOn(UserController.class).createUser(entity))
                .withRel(CREATE_REL);
        Link update = linkTo(methodOn(UserController.class).updateUser(entity.getId(), entity))
                .withRel(UPDATE_REL);
        Link updateUserRole = linkTo(methodOn(UserController.class).updateUserRole(entity.getId(), entity.getUserRole()))
                .withRel(UPDATE_ROLE_REL);
        Link updateUserStatus = linkTo(methodOn(UserController.class).updateUserStatus(entity.getId(), entity.getUserStatus()))
                .withRel(UPDATE_STATUS_REL);
        userModel.add(getUserByLogin, create, update, updateUserRole, updateUserStatus);
        return userModel;
    }

    public List<UserModel> toListModel(List<UserDTO> entity){
        List<UserModel> userModels = new ArrayList<>();
        for (int i = 0; i < entity.size(); i++) {
          UserModel userModel = toModel(entity.get(i));
          userModels.add(userModel);
        }
        return userModels;
    }

}
