package com.goosfraba.city_parking.vehicles.controller;

import com.goosfraba.city_parking.vehicles.dto.VehicleDto;
import com.goosfraba.city_parking.vehicles.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{cityCode}")
    public ResponseEntity<List<VehicleDto>> getVehiclesByCityCode(@PathVariable  String cityCode) {
        return new ResponseEntity<>(vehicleService.getVehiclesByCityCode(cityCode), HttpStatus.OK);
    }

    @GetMapping("/park/{vehicleType}/{vehicleId}/{facilityName}")
    public ResponseEntity<String> parkVehicle(
            @PathVariable String vehicleType,
            @PathVariable Integer vehicleId,
            @PathVariable String facilityName
    ) {
        return new ResponseEntity<>(vehicleService.parkVehicle(vehicleType, vehicleId, facilityName), HttpStatus.OK);
    }

    @GetMapping("/unPark/{vehicleType}/{vehicleId}")
    public ResponseEntity<String> unParkVehicle(
            @PathVariable String vehicleType,
            @PathVariable Integer vehicleId

    ) {
        return new ResponseEntity<>(vehicleService.unParkVehicle(vehicleType, vehicleId), HttpStatus.OK);
    }
}
