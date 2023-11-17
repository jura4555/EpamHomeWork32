package com.epam.spring.travel_agency.rest_template.service.reference;

public abstract class RestTemplateReference {
    //--------------------------------------------------Hotel---------------------------------------------------------//
    public static final String GET_ALL_HOTEL_URL = "http://localhost:8082/api/v1/hotel";
    public static final String GET_HOTEL_BY_NAME_URL = "http://localhost:8082/api/v1/hotel/{name}";
    public static final String CREATE_HOTEL_URL = "http://localhost:8082/api/v1/hotel";
    public static final String UPDATE_HOTEL_URL = "http://localhost:8082/api/v1/hotel/{id}";
    public static final String DELETE_HOTEL_URL = "http://localhost:8082/api/v1/hotel/{id}";
    //--------------------------------------------------User---------------------------------------------------------//
    public static final String GET_USER_BY_LOGIN_URL = "http://localhost:8082/api/v1/user/login/{login}";
    public static final String GET_USER_BY_ROLE_URL = "http://localhost:8082/api/v1/user/role/{userRole}";
    public static final String CREATE_USER_URL = "http://localhost:8082/api/v1/user";
    public static final String UPDATE_USER_URL = "http://localhost:8082/api/v1/user/{id}";
    public static final String UPDATE_USER_ROLE_URL = "http://localhost:8082/api/v1/user/{id}/role?userRole={userRole}";
    public static final String UPDATE_USER_STATUS_URL = "http://localhost:8082/api/v1/user/{id}/status?userStatus={userStatus}";
    public static final String GET_USER_DETAILS_URL = "http://localhost:8082/api/v1/user/{id}/user_details";
    //--------------------------------------------------Tour---------------------------------------------------------//
    public static final String GET_ALL_TOUR_URL = "http://localhost:8082/api/v1/tour";
    public static final String GET_TOUR_BY_NAME_URL = "http://localhost:8082/api/v1/tour/name/{name}";
    public static final String GET_TOUR_BY_TYPE_URL = "http://localhost:8082/api/v1/tour/type/{tourType}";
    public static final String GET_TOUR_BY_COUNT_URL = "http://localhost:8082/api/v1/tour/count/{count}";
    public static final String GET_TOUR_BY_PRICE_URL = "http://localhost:8082/api/v1/tour/price/{minPrice}/{maxPrice}";
    public static final String GET_TOUR_BY_HOTEL_TYPE_URL = "http://localhost:8082/api/v1/tour/hotel/{hotelType}";
    public static final String CREATE_TOUR_URL = "http://localhost:8082/api/v1/tour";
    public static final String UPDATE_TOUR_URL = "http://localhost:8082/api/v1/tour/{id}";
    public static final String UPDATE_TOUR_BURNING_URL = "http://localhost:8082/api/v1/tour/{id}/burning?burning={burning}";
    public static final String UPDATE_TOUR_MAXDISCOUNT_URL =  "http://localhost:8082/api/v1/tour/{id}/maxdiscount?maxDisCount={maxDisCount}";
    public static final String DELETE_TOUR_URL = "http://localhost:8082/api/v1/tour/{id}";
    //--------------------------------------------------Order---------------------------------------------------------//
    public static final String GET_ALL_ORDER_URL = "http://localhost:8082/api/v1/order";
    public static final String GET_ORDER_BY_ID_URL = "http://localhost:8082/api/v1/order/id/{id}";
    public static final String GET_ORDER_BY_STATUS_URL = "http://localhost:8082/api/v1/order/tour/{tourStatus}";
    public static final String CREATE_ORDER_URL = "http://localhost:8082/api/v1/order";
    public static final String UPDATE_ORDER_DESCRIPTION_URL = "http://localhost:8082/api/v1/order/{id}/description?description={description}";
    public static final String UPDATE_ORDER_PRICE_URL = "http://localhost:8082/api/v1/order/{id}/price?stepDisCount={stepDisCount}";
    public static final String UPDATE_ORDER_STATUS_URL = "http://localhost:8082/api/v1/order/{id}/status?tourStatus={tourStatus}";
}