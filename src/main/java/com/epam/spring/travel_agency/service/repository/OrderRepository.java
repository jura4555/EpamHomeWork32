package com.epam.spring.travel_agency.service.repository;


import com.epam.spring.travel_agency.service.model.Order;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByTourStatus(TourStatus tourStatus);

}
