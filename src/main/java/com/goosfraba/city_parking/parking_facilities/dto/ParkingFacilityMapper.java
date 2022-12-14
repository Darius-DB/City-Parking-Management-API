package com.goosfraba.city_parking.parking_facilities.dto;

import com.goosfraba.city_parking.parking_facilities.model.ParkingFacility;

public class ParkingFacilityMapper {

    public static ParkingFacilityDto toFacilityDto(ParkingFacility facility) {
        return ParkingFacilityDto.builder()
                .id(facility.getId())
                .name(facility.getName())
                .capacity(facility.getCapacity())
                .availableCapacity(facility.getAvailableCapacity())
                .city(facility.getCity().getName())
                .build();
    }

    public static ParkingFacility toFacility(ParkingFacilityDto facilityDto) {

        if (facilityDto.getAvailableCapacity() > facilityDto.getCapacity()) {
            throw new IllegalArgumentException("Available capacity can't be higher than initial capacity");
        }

        return ParkingFacility.builder()
                .name(facilityDto.getName())
                .capacity(facilityDto.getCapacity())
                .availableCapacity(facilityDto.getAvailableCapacity())
                .build();

    }
}
