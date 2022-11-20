package com.goosfraba.city_parking.cities.controller;

import com.goosfraba.city_parking.cities.dto.CityDto;
import com.goosfraba.city_parking.cities.service.CityService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable Integer id) {
        return new ResponseEntity<>(cityService.getCityById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CityDto> getCityByCode(@RequestParam   String cityCode) {
        return new ResponseEntity<>(cityService.getCityByCode(cityCode), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CityDto>> getAllCities() {
        return new ResponseEntity<>(cityService.getAllCities(), HttpStatus.OK);
    }


}
