package com.goosfraba.city_parking.vehicles.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VehicleDto {

    protected Integer id;
    protected Boolean isParked;
    @NotBlank
    protected String city;
    protected String parkingFacility;
}
