package com.goosfraba.city_parking.cities.dto;

import com.goosfraba.city_parking.cities.model.City;

public class CityMapper {

    public static CityDto toCityDto(City city) {
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .code(city.getCode())
                .build();
    }

    public static City toCity(CityDto cityDto) {
        return City.builder()
                .name(cityDto.getName())
                .code(cityDto.getName().substring(0, 3).toUpperCase())
                .build();

    }
}
