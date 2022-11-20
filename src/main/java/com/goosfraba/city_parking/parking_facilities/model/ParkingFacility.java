package com.goosfraba.city_parking.parking_facilities.model;

import com.goosfraba.city_parking.cities.model.City;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity(name = "parking_facilities")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ParkingFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Column(length = 256, unique = true, nullable = false)
    protected String name;


    protected Integer capacity = 0;

    @Column(name = "available_capacity")
    protected  Integer availableCapacity = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected City city;

}
