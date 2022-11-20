package com.goosfraba.city_parking.cities.model;

import lombok.*;

import javax.persistence.*;

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



    public City(String name) {
        this.name = name;
    }
}
