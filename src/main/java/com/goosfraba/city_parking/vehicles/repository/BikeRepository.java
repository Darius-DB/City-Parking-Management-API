package com.goosfraba.city_parking.vehicles.repository;

import com.goosfraba.city_parking.parking_facilities.model.BikeRack;
import com.goosfraba.city_parking.vehicles.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer> {
}
