package com.goosfraba.city_parking.cities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CityDto {

    private Integer id;

    @NotBlank @Length(max = 256)
    private String name;

    private String code;
}
