package com.epam.spring.travel_agency.service.repository;

import com.epam.spring.travel_agency.service.model.Tour;
import com.epam.spring.travel_agency.service.model.enums.HotelType;
import com.epam.spring.travel_agency.service.model.enums.TourType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {

    List<Tour> findAll();

    Page<Tour> findAll(Pageable pageable);
    Optional<Tour> findByName(String name);

    List<Tour> findByTourType(TourType tourType);

    Page<Tour> findByTourType(TourType tourType, Pageable pageable);


    List<Tour> findByPlaceCount(int placeCount);

    Page<Tour> findByPlaceCount(int placeCount, Pageable pageable);


    @Query("SELECT new Tour(t.id, t.name, t.price, t.dateDaparture, t.dateArrival, t.placeDaparture, " +
            "t.placeArrival, t.maxDisCount, t.placeCount, t.hotel, t.tourType, t.burning) " +
            "FROM Tour t WHERE t.price BETWEEN ?1 and ?2")
    List<Tour> findByPrice(double minPrice, double maxPrice);


    @Query(value = "SELECT new Tour(t.id, t.name, t.price, t.dateDaparture, t.dateArrival, t.placeDaparture, " +
            "t.placeArrival, t.maxDisCount, t.placeCount, t.hotel, t.tourType, t.burning) " +
            "FROM Tour t WHERE t.price BETWEEN ?1 and ?2",
            countQuery = "SELECT count(t)" +
            "FROM Tour t WHERE t.price BETWEEN ?1 and ?2")
    Page<Tour> findByPrice(double minPrice, double maxPrice, Pageable pageable);

    @Query("SELECT new Tour(t.id, t.name, t.price, t.dateDaparture, t.dateArrival, t.placeDaparture, " +
            "t.placeArrival, t.maxDisCount, t.placeCount, t.hotel, t.tourType, t.burning)" +
            "From Tour t INNER JOIN t.hotel h WHERE h.hotelType = ?1")
    List<Tour> findByHotelType(HotelType hotelType);

    @Query(value = "SELECT new Tour(t.id, t.name, t.price, t.dateDaparture, t.dateArrival, t.placeDaparture, " +
            "t.placeArrival, t.maxDisCount, t.placeCount, t.hotel, t.tourType, t.burning)" +
            "From Tour t INNER JOIN t.hotel h WHERE h.hotelType = ?1",
            countQuery = "SELECT count(t)" +
            "From Tour t INNER JOIN t.hotel h WHERE h.hotelType = ?1")
    Page<Tour> findByHotelType(HotelType hotelType, Pageable pageable);

    boolean existsById(int id);

    boolean existsByName(String name);
}
