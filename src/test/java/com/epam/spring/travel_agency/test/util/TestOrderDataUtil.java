package com.epam.spring.travel_agency.test.util;

import com.epam.spring.travel_agency.controller.dto.OrderDTO;
import com.epam.spring.travel_agency.service.model.Order;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;

import java.util.Random;

public class TestOrderDataUtil {

    public static final int ORDER_1_ID = 1;
    public static  final String ORDER_1_DESCRIPTION = "This is first order";
    public static final TourStatus ORDER_1_TOUR_STATUS = TourStatus.REGISTERED;

    public static final int ORDER_2_ID = 2;
    public static  final String ORDER_2_DESCRIPTION = "This is second order";
    public static final TourStatus ORDER_2_TOUR_STATUS = TourStatus.REGISTERED;

    public static  final String ORDER_UPDATE_DESCRIPTION = "This is updated order";


    public static final int ORDER_UPDATE_STEP_DIS_COUNT = 5;
    public static final int ORDER_UPDATE_DIS_COUNT = 10;
    public static final double ORDER_UPDATE_PRICE = getRandomPrice(ORDER_UPDATE_DIS_COUNT);


    public static Order getOrder1(){
        return Order.builder()
                .id(ORDER_1_ID)
                .user(TestUserDataUtil.getUser1())
                .tour(TestTourDataUtil.getTour1())
                .tourStatus(ORDER_1_TOUR_STATUS)
                .description(ORDER_1_DESCRIPTION)
                .build();
    }

    public static OrderDTO getOrderDTO1(){
        return OrderDTO.builder()
                .id(ORDER_1_ID)
                .user(TestUserDataUtil.getUserDTO1())
                .tour(TestTourDataUtil.getTourDTO1())
                .tourStatus(ORDER_1_TOUR_STATUS)
                .description(ORDER_1_DESCRIPTION)
                .build();
    }

    public static Order getOrder2(){
        return Order.builder()
                .id(ORDER_2_ID)
                .user(TestUserDataUtil.getUser2())
                .tour(TestTourDataUtil.getTour2())
                .tourStatus(ORDER_2_TOUR_STATUS)
                .description(ORDER_2_DESCRIPTION)
                .build();
    }

    public static OrderDTO getOrderDTO2(){
        return OrderDTO.builder()
                .id(ORDER_2_ID)
                .user(TestUserDataUtil.getUserDTO2())
                .tour(TestTourDataUtil.getTourDTO2())
                .tourStatus(ORDER_2_TOUR_STATUS)
                .description(ORDER_2_DESCRIPTION)
                .build();
    }

    public static Order getOrderForCreate(){
        return Order.builder()
                .id(ORDER_1_ID)
                .user(TestUserDataUtil.getUser1())
                .tour(TestTourDataUtil.getTour1())
                .description(ORDER_1_DESCRIPTION)
                .build();
    }

    public static OrderDTO getOrderDTOForCreate(){
        return OrderDTO.builder()
                .user(TestUserDataUtil.getUserDTO1())
                .tour(TestTourDataUtil.getTourDTO1())
                .description(ORDER_1_DESCRIPTION)
                .build();
    }

    public static Order getOrderForUpdatePrice(){
        return Order.builder()
                .id(ORDER_1_ID)
                .user(TestUserDataUtil.getUser1())
                .tour(TestTourDataUtil.getTour1())
                .price(ORDER_UPDATE_PRICE)
                .stepDisCount(ORDER_UPDATE_STEP_DIS_COUNT)
                .disCount(ORDER_UPDATE_DIS_COUNT)
                .tourStatus(TourStatus.REGISTERED)
                .description(ORDER_1_DESCRIPTION)
                .build();
    }

    public static OrderDTO getOrderDTOForUpdatePrice(){
        return OrderDTO.builder()
                .id(ORDER_1_ID)
                .user(TestUserDataUtil.getUserDTO1())
                .tour(TestTourDataUtil.getTourDTO1())
                .price(ORDER_UPDATE_PRICE)
                .stepDisCount(ORDER_UPDATE_STEP_DIS_COUNT)
                .disCount(ORDER_UPDATE_DIS_COUNT)
                .tourStatus(TourStatus.REGISTERED)
                .description(ORDER_1_DESCRIPTION)
                .build();
    }

    public static Order getOrderForMaxDisCountException(){
        return Order.builder()
                .id(ORDER_1_ID)
                .user(TestUserDataUtil.getUser1())
                .tour(TestTourDataUtil.getTourForCreate())
                .tourStatus(TourStatus.REGISTERED)
                .description(ORDER_1_DESCRIPTION)
                .build();
    }

    public static Order getOrderForUpdateStatus(){
        return Order.builder()
                .id(ORDER_1_ID)
                .user(TestUserDataUtil.getUser1())
                .tour(TestTourDataUtil.getTourForOrderUpdateStatus())
                .price(ORDER_UPDATE_PRICE)
                .stepDisCount(ORDER_UPDATE_STEP_DIS_COUNT)
                .disCount(ORDER_UPDATE_DIS_COUNT)
                .tourStatus(TourStatus.PAID)
                .description(ORDER_1_DESCRIPTION)
                .build();
    }

    public static OrderDTO getOrderDTOForUpdateStatus(){
        return OrderDTO.builder()
                .id(ORDER_1_ID)
                .user(TestUserDataUtil.getUserDTO1())
                .tour(TestTourDataUtil.getTourDTOForOrderUpdateStatus())
                .price(ORDER_UPDATE_PRICE)
                .stepDisCount(ORDER_UPDATE_STEP_DIS_COUNT)
                .disCount(ORDER_UPDATE_DIS_COUNT)
                .tourStatus(TourStatus.PAID)
                .description(ORDER_1_DESCRIPTION)
                .build();
    }


    private static double getRandomPrice(int discount){
        return (TestTourDataUtil.TOUR_1_PRICE - (TestTourDataUtil.TOUR_1_PRICE * discount/100));

    }
}
