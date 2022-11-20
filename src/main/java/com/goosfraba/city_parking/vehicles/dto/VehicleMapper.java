package com.goosfraba.city_parking.vehicles.dto;

import com.goosfraba.city_parking.parking_facilities.dto.ParkingFacilityDto;
import com.goosfraba.city_parking.parking_facilities.model.ParkingFacility;
import com.goosfraba.city_parking.vehicles.model.Vehicle;

public class VehicleMapper {

    public static VehicleDto toVehicleDto(Vehicle vehicle) {
        return VehicleDto.builder()
                .id(vehicle.getId())
                .isParked(vehicle.getIsParked())
                .city(vehicle.getCity().getName())
                .parkingFacility(vehicle.getParkingFacility().getName())
                .build();
    }

    public static Vehicle toVehicle(VehicleDto vehicleDto) {

        return Vehicle.builder()
                .isParked(false)
                .build();
    }

}
