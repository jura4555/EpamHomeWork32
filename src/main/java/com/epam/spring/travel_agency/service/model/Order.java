package com.epam.spring.travel_agency.service.model;

import com.epam.spring.travel_agency.service.model.converter.TourStatusConverter;
import com.epam.spring.travel_agency.service.model.enums.TourStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordering")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Tour tour;

    @Column
    private double price;

    @Column(name = "step_discount")
    private int stepDisCount;

    @Column(name = "discount")
    private int disCount;

    @Convert(converter = TourStatusConverter.class)
    @Column(name = "tour_staus_id")
    private TourStatus tourStatus;

    @Column(name = "ordering_description")
    private String description;
}
