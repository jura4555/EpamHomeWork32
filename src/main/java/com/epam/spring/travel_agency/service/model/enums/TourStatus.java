package com.epam.spring.travel_agency.service.model.enums;

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
