package com.goosfraba.city_parking.cities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CityDto {

    private String id;

    @NotBlank @Max(256)
    private String name;

    private String code;
}
