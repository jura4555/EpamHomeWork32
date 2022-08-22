package com.epam.spring.homework4.service.model.enums;

public enum TourStatus {
    REGISTERED(1), PAID(2), CANCELED(3);

    int index;
    TourStatus(int i){
        index = 1;
    }

    public int getIndex() {
        return index;
    }

    public static TourStatus getTourStatus(int i){
        return TourStatus.values()[i-1];
    }
}
