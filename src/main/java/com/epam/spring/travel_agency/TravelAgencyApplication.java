package com.epam.spring.travel_agency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TravelAgencyApplication {
    public static void main(String[] args) {
        SpringApplication.run(TravelAgencyApplication.class);
    }
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
