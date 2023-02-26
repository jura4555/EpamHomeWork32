package com.epam.spring.travel_agency.service.model.converter;

import com.epam.spring.travel_agency.service.model.enums.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserRoleConverter implements AttributeConverter<UserRole, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserRole userRole) {
        if(userRole == null) {
            return null;
        }

        switch (userRole) {
            case USER:
                return 1;
            case MANAGER:
                return 2;
            case ADMIN:
                return 3;
            default:
                throw new IllegalArgumentException(userRole + " not supported.");
        }

    }

    @Override
    public UserRole convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        switch (dbData) {
            case 1:
                return UserRole.USER;
            case 2:
                return UserRole.MANAGER;
            case 3:
                return UserRole.ADMIN;
            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }

}
