package com.goosfraba.city_parking.parking_facilities.controller;

import com.goosfraba.city_parking.cities.dto.CityDto;
import com.goosfraba.city_parking.parking_facilities.dto.ParkingFacilityDto;
import com.goosfraba.city_parking.parking_facilities.service.ParkingFacilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/facility")
public class ParkingFacilityController {

    private final ParkingFacilityService parkingFacilityService;

    public ParkingFacilityController(ParkingFacilityService parkingFacilityService) {
        this.parkingFacilityService = parkingFacilityService;
    }


    @PostMapping
    public ResponseEntity<ParkingFacilityDto> createFacility(@Validated @RequestBody ParkingFacilityDto facilityDto,
                                                             @RequestParam String facilityType) {
        return new ResponseEntity<>(parkingFacilityService.createFacility(facilityDto, facilityType), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ParkingFacilityDto> getFacilityById(@RequestParam Integer id, @RequestParam String facilityType) {
        return new ResponseEntity<>(parkingFacilityService.getFacilityById(id, facilityType), HttpStatus.OK);
    }
}
