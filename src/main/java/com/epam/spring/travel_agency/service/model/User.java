package com.epam.spring.travel_agency.service.model;

import com.epam.spring.travel_agency.service.model.converter.UserRoleConverter;
import com.epam.spring.travel_agency.service.model.converter.UserStatusConverter;
import com.epam.spring.travel_agency.service.model.enums.UserRole;
import com.epam.spring.travel_agency.service.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_details_id", unique = true)
    private UserDetails userDetails;

    @Column(name = "user_role_id", nullable = false)
    @Convert(converter = UserRoleConverter.class)
    private UserRole userRole;

    @Column(name = "user_status_id", nullable = false)
    @Convert(converter = UserStatusConverter.class)
    private UserStatus userStatus;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
