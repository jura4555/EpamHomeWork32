package com.epam.spring.travel_agency.service.repository;

import com.epam.spring.travel_agency.service.model.User;
import com.epam.spring.travel_agency.service.model.UserDetails;
import com.epam.spring.travel_agency.service.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByLogin(String login);

    Optional<User> findById(int id);

    Optional<User> findByLogin(String login);

    List<User> findByUserRole(UserRole userRole);

}
