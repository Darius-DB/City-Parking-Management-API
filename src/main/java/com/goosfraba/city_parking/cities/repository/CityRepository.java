package com.goosfraba.city_parking.cities.repository;

import com.goosfraba.city_parking.cities.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
    public Optional<City> findByCode(String code);
}
