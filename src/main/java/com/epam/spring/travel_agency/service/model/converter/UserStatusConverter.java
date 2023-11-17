package com.epam.spring.travel_agency.service.model.converter;

import com.epam.spring.travel_agency.service.model.enums.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserStatusConverter implements AttributeConverter<UserStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserStatus userStatus) {
        if(userStatus == null){
            return null;
        }
        switch (userStatus) {
            case ACTIVE:
                return 0;
            case BLOCKED:
                return 1;
            default:
                throw new IllegalArgumentException(userStatus + " not supported.");
        }
    }

    @Override
    public UserStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }

        switch (dbData){
            case 0:
                return UserStatus.ACTIVE;
            case 1:
                return UserStatus.BLOCKED;
            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }
}
