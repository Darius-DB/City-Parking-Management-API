package com.goosfraba.city_parking.parking_facilities.repository;

import com.goosfraba.city_parking.parking_facilities.model.ParkingFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingFacilityRepository extends JpaRepository<ParkingFacility, Integer> {
    public ParkingFacility findByName(String name);
}
