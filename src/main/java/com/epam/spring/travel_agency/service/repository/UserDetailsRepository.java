package com.epam.spring.travel_agency.service.repository;

import com.epam.spring.travel_agency.service.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
