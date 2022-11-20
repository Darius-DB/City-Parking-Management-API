package com.goosfraba.city_parking.parking_facilities.repository;

import com.goosfraba.city_parking.parking_facilities.model.BikeRack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BikeRackRepository extends JpaRepository<BikeRack, Integer> {

    public Optional<BikeRack> findByName(String name);

    public List<BikeRack> findAllByCityName(String cityName);


}
