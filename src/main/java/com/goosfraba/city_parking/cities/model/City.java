package com.goosfraba.city_parking.cities.model;

import com.goosfraba.city_parking.vehicles.model.Vehicle;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 256, unique = true, nullable = false)
    private String name;

    @Column(length = 4, nullable = false, unique = true)
    private String code;

    @OneToMany(mappedBy="city")
    private List<Vehicle> vehicles = new ArrayList<>();


    public City(String name) {
        this.name = name;
    }
}
