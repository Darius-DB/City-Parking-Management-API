package com.goosfraba.city_parking.parking_facilities.model;

import com.goosfraba.city_parking.cities.model.City;
import com.goosfraba.city_parking.vehicles.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "parking_facilities")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ParkingFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected String id;

    @Column(length = 256, unique = true, nullable = false)
    protected String name;


    protected Integer capacity;

    @Column(name = "available_capacity")
    protected  Integer availableCapacity;

    @ManyToOne
    @JoinColumn(name="city_id", nullable=false)
    protected City city;

    @OneToMany(mappedBy="parkingFacility")
    private List<Vehicle> vehicles;
}
