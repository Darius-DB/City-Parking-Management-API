package com.goosfraba.city_parking.vehicles.repository;

import com.goosfraba.city_parking.vehicles.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
 public List<Car> findAllByCityCode(String cityCode);
}
