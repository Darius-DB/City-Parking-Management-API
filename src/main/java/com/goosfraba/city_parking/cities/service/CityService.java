package com.goosfraba.city_parking.cities.service;

import com.goosfraba.city_parking.cities.dto.CityDto;
import com.goosfraba.city_parking.cities.dto.CityMapper;
import com.goosfraba.city_parking.cities.model.City;
import com.goosfraba.city_parking.cities.repository.CityRepository;
import com.goosfraba.city_parking.exceptions.ResourceAlreadyPresentException;
import com.goosfraba.city_parking.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public CityDto createCity(CityDto cityDto) {
        City cityToSave = CityMapper.toCity(cityDto);

        if (cityRepository.findByCode(cityToSave.getCode()).isPresent()) {
            throw new ResourceAlreadyPresentException(cityToSave.getName() + " already exists in our database");
        }

        cityRepository.save(cityToSave);

        return CityMapper.toCityDto(cityToSave);
    }

    public CityDto getCityById(Integer id) {
        return  CityMapper.toCityDto(
                cityRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("There is no city with this id")));
    }

    public CityDto getCityByCode(String cityCode) {

        if (cityCode.length() != 3) {
            throw new IllegalArgumentException("City codes must be 3 letters long");
        }

        return CityMapper.toCityDto(
                cityRepository.findByCode(cityCode).orElseThrow(
                        () -> new ResourceNotFoundException("There is no city with this code")));
    }

    public List<CityDto> getAllCities() {
        return cityRepository.findAll().stream().map(CityMapper::toCityDto).collect(Collectors.toList());
    }
}
