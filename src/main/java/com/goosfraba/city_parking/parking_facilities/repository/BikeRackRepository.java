package com.goosfraba.city_parking.parking_facilities.repository;

import com.goosfraba.city_parking.parking_facilities.model.BikeRack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRackRepository extends JpaRepository<BikeRack, Integer> {
}
