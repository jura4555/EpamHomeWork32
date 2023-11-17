package com.epam.spring.travel_agency.service.impl.randomizers;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MyDisCountRandomizer {

    public int getRandomDisCount(int max, int step){
        Random random = new Random();
        int randNumber = random.nextInt(max / step) + 1;
        return step * randNumber;
    }
}
