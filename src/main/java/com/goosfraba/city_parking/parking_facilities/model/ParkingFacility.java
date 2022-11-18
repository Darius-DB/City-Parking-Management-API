package com.goosfraba.city_parking.parking_facilities.model;

import com.goosfraba.city_parking.cities.model.City;
import com.goosfraba.city_parking.vehicles.model.Vehicle;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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

    @ManyToOne
    @JoinColumn(name="city_id", nullable=false)
    protected City city;

    @OneToMany(mappedBy="parkingFacility")
    private List<Vehicle> vehicles;
}
