package com.epam.spring.homework4.service.model.enums;

public enum UserRole {
    USER(1), REGISTERED_USER(2), MANAGER(3), ADMIN(4);
    int index;

    UserRole(int i){
        index = i;
    }

    public int getIndex() {
        return index;
    }

    public static UserRole getUserRole(int i){
        return UserRole.values()[i-1];
    }

}
