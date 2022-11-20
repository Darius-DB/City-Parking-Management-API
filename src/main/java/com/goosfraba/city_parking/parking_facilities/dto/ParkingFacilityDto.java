package com.goosfraba.city_parking.parking_facilities.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ParkingFacilityDto {

    protected Integer id;
    @NotBlank @Length(max = 256)
    protected String name;
    @Min(0)
    protected Integer capacity;
    @Min(0)
    protected Integer availableCapacity;
    @NotBlank @Length(max = 256)
    protected String city;

}
