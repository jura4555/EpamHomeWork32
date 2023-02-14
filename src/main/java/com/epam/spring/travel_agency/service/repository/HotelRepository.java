package com.epam.spring.travel_agency.service.repository;

import com.epam.spring.travel_agency.service.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    Optional<Hotel> findByName(String name);

    boolean existsById(int id);

    boolean existsByNameAndCity(String name, String city);

    boolean existsByName(String name);
}
