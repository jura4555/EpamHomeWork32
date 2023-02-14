package com.epam.spring.travel_agency.service.repository;

import com.epam.spring.travel_agency.service.model.Tour;
import com.epam.spring.travel_agency.service.model.enums.HotelType;
import com.epam.spring.travel_agency.service.model.enums.TourType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, Integer> {

    List<Tour> findAll();
    Optional<Tour> findByName(String name);

    List<Tour> findByTourType(TourType tourType);

    List<Tour> findByPlaceCount(int placeCount);

    @Query("SELECT new Tour(t.id, t.name, t.price, t.dateDaparture, t.dateArrival, t.placeDaparture, " +
            "t.placeArrival, t.maxDisCount, t.placeCount, t.hotel, t.tourType, t.burning) " +
            "FROM Tour t WHERE t.price BETWEEN ?1 and ?2")
    List<Tour> findByPrice(double minPrice, double maxPrice);

    @Query("SELECT new Tour(t.id, t.name, t.price, t.dateDaparture, t.dateArrival, t.placeDaparture, " +
            "t.placeArrival, t.maxDisCount, t.placeCount, t.hotel, t.tourType, t.burning)" +
            "From Tour t INNER JOIN t.hotel h WHERE h.hotelType = ?1")
    List<Tour> findByHotelType(HotelType hotelType);

    boolean existsById(int id);

    boolean existsByName(String name);
}
