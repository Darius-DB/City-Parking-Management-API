package com.goosfraba.city_parking.vehicles.controller;

import com.goosfraba.city_parking.parking_facilities.dto.ParkingFacilityDto;
import com.goosfraba.city_parking.vehicles.dto.VehicleDto;
import com.goosfraba.city_parking.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<VehicleDto> createVehicle(@Validated @RequestBody VehicleDto vehicleDto,
                                                     @RequestParam String vehicleType) {
        return new ResponseEntity<>(vehicleService.createVehicle(vehicleDto, vehicleType), HttpStatus.CREATED);
    }
}
