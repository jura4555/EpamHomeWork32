package com.epam.spring.travel_agency.service.repository;

import com.epam.spring.travel_agency.service.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
