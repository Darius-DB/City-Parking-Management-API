package com.goosfraba.city_parking.vehicles.model;

import com.goosfraba.city_parking.cities.model.City;
import com.goosfraba.city_parking.parking_facilities.model.ParkingFacility;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "vehicles")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer id;

    @Column(name = "is_parked")
    protected Boolean isParked = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected City city;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_facility_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected ParkingFacility parkingFacility;
}
