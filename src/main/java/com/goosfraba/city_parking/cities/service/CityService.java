package com.goosfraba.city_parking.cities.service;

import com.goosfraba.city_parking.cities.dto.CityDto;
import com.goosfraba.city_parking.cities.dto.CityMapper;
import com.goosfraba.city_parking.cities.model.City;
import com.goosfraba.city_parking.cities.repository.CityRepository;
import com.goosfraba.city_parking.exceptions.CityAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new CityAlreadyExistsException(cityToSave.getName() + " already exists in our database");
        }

        cityRepository.save(cityToSave);

        return CityMapper.toCityDto(cityToSave);
    }
}
