package com.goosfraba.city_parking.cities.controller;

import com.goosfraba.city_parking.cities.dto.CityDto;
import com.goosfraba.city_parking.cities.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/")
    public ResponseEntity<CityDto> createCity(@RequestBody @Validated CityDto cityDto) {
        return new ResponseEntity<>(cityService.createCity(cityDto), HttpStatus.CREATED);
    }
}
