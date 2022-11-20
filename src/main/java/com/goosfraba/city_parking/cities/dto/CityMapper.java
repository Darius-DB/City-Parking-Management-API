package com.goosfraba.city_parking.cities.dto;

import com.goosfraba.city_parking.cities.model.City;
import com.goosfraba.city_parking.utils.InputFormatter;

public class CityMapper {

    public static CityDto toCityDto(City city) {
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .code(city.getCode())
                .build();
    }

    public static City toCity(CityDto cityDto) {

        String formattedCityName = InputFormatter.formatCityName(cityDto.getName());
        String formattedCityCode = InputFormatter.formatCityCode(cityDto.getName());

        return City.builder()
                .name(formattedCityName)
                .code(formattedCityCode)
                .build();

    }
}
