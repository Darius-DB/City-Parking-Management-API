package com.goosfraba.city_parking.vehicles.service;

import com.goosfraba.city_parking.vehicles.repository.BikeRepository;
import com.goosfraba.city_parking.vehicles.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final CarRepository carRepository;
    private final BikeRepository bikeRepository;

    @Autowired
    public VehicleService(CarRepository carRepository, BikeRepository bikeRepository) {
        this.carRepository = carRepository;
        this.bikeRepository = bikeRepository;
    }


}
