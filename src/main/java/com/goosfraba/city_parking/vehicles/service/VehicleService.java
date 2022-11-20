package com.goosfraba.city_parking.vehicles.service;

import com.goosfraba.city_parking.cities.model.City;
import com.goosfraba.city_parking.cities.repository.CityRepository;
import com.goosfraba.city_parking.exceptions.ResourceAlreadyPresentException;
import com.goosfraba.city_parking.exceptions.ResourceNotFoundException;
import com.goosfraba.city_parking.parking_facilities.dto.ParkingFacilityDto;
import com.goosfraba.city_parking.parking_facilities.dto.ParkingFacilityMapper;
import com.goosfraba.city_parking.parking_facilities.model.BikeRack;
import com.goosfraba.city_parking.parking_facilities.model.CarPark;
import com.goosfraba.city_parking.utils.InputFormatter;
import com.goosfraba.city_parking.vehicles.dto.VehicleDto;
import com.goosfraba.city_parking.vehicles.dto.VehicleMapper;
import com.goosfraba.city_parking.vehicles.model.Bike;
import com.goosfraba.city_parking.vehicles.model.Car;
import com.goosfraba.city_parking.vehicles.model.Vehicle;
import com.goosfraba.city_parking.vehicles.repository.BikeRepository;
import com.goosfraba.city_parking.vehicles.repository.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private final CarRepository carRepository;
    private final BikeRepository bikeRepository;
    private final CityRepository cityRepository;

    @Autowired
    public VehicleService(CarRepository carRepository, BikeRepository bikeRepository, CityRepository cityRepository) {
        this.carRepository = carRepository;
        this.bikeRepository = bikeRepository;
        this.cityRepository = cityRepository;
    }


    public VehicleDto createVehicle(VehicleDto vehicleDto, String vehicleType) {
        Vehicle vehicleToSave = VehicleMapper.toVehicle(vehicleDto);
        City requestedCity = cityRepository.findByName(InputFormatter.formatCityName(vehicleDto.getCity())).orElseThrow(
                () -> new ResourceNotFoundException("No such city")
        );


        if (vehicleType.trim().equalsIgnoreCase("bike")) {
            vehicleToSave.setCity(requestedCity);
            Bike bikeToSave = new Bike();
            BeanUtils.copyProperties(vehicleToSave,bikeToSave);
            bikeRepository.save(bikeToSave);
        } else if (vehicleType.trim().equalsIgnoreCase("car")) {
            vehicleToSave.setCity(requestedCity);
            Car carToSave = new Car();
            BeanUtils.copyProperties(vehicleToSave,carToSave);
            carRepository.save(carToSave);
        } else {
            throw new IllegalArgumentException("We do not support this type of vehicle");
        }

        return VehicleMapper.toVehicleDto(vehicleToSave);
    }

    public List<VehicleDto> getVehiclesByCityCode(String cityCode) {
        List<VehicleDto> vehicles = new ArrayList<>();
        String formattedCityCode = InputFormatter.formatCityCode(cityCode);

        vehicles.addAll(bikeRepository.findAllByCityCode(formattedCityCode).stream().map(
                VehicleMapper::toVehicleDto).collect(Collectors.toList()));
        vehicles.addAll(carRepository.findAllByCityCode(formattedCityCode).stream().map(
                VehicleMapper::toVehicleDto).collect(Collectors.toList()));

        return vehicles;
    }
}
