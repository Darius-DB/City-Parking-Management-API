package com.goosfraba.city_parking.vehicles.service;

import com.goosfraba.city_parking.cities.model.City;
import com.goosfraba.city_parking.cities.repository.CityRepository;
import com.goosfraba.city_parking.cities.service.CityService;
import com.goosfraba.city_parking.exceptions.ResourceNotFoundException;
import com.goosfraba.city_parking.parking_facilities.model.BikeRack;
import com.goosfraba.city_parking.parking_facilities.model.CarPark;
import com.goosfraba.city_parking.parking_facilities.repository.BikeRackRepository;
import com.goosfraba.city_parking.parking_facilities.repository.CarParkRepository;
import com.goosfraba.city_parking.utils.InputFormatter;
import com.goosfraba.city_parking.utils.Validator;
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
    private final BikeRackRepository bikeRackRepository;
    private final CarParkRepository carParkRepository;
    private final CityService cityService;

    @Autowired
    public VehicleService(CarRepository carRepository, BikeRepository bikeRepository, CityRepository cityRepository, BikeRackRepository bikeRackRepository, CarParkRepository carParkRepository, CityService cityService) {
        this.carRepository = carRepository;
        this.bikeRepository = bikeRepository;
        this.cityRepository = cityRepository;
        this.bikeRackRepository = bikeRackRepository;
        this.carParkRepository = carParkRepository;
        this.cityService = cityService;
    }


    public VehicleDto createVehicle(VehicleDto vehicleDto, String vehicleType) {
        Vehicle vehicleToSave = VehicleMapper.toVehicle(vehicleDto);
        City requestedCity = cityService.getCityByName(vehicleDto.getCity());


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
        Validator.checkCityCodeLength(cityCode);
        String formattedCityCode = InputFormatter.formatCityCode(cityCode);

        vehicles.addAll(bikeRepository.findAllByCityCode(formattedCityCode).stream().map(
                VehicleMapper::toVehicleDto).collect(Collectors.toList()));
        vehicles.addAll(carRepository.findAllByCityCode(formattedCityCode).stream().map(
                VehicleMapper::toVehicleDto).collect(Collectors.toList()));

        return vehicles;
    }

    public String parkVehicle(String vehicleType, Integer vehicleId, String facilityName) {
        if (vehicleType.trim().equalsIgnoreCase("bike")) {
            Bike bikeToBeParked = bikeRepository.findById(vehicleId).orElseThrow(
                    () -> new ResourceNotFoundException("We do not have this vehicle"));

            BikeRack bikeRack = bikeRackRepository.findByName(facilityName).orElseThrow(
                    () -> new ResourceNotFoundException("We do not have this facility"));

            if (!bikeToBeParked.getCity().getName().equalsIgnoreCase(bikeRack.getCity().getName())) {
                throw new IllegalArgumentException("The vehicle and the facility must be in the same city");
            }

            bikeToBeParked.setIsParked(true);
            bikeToBeParked.setParkingFacility(bikeRack);
            bikeRepository.save(bikeToBeParked);
        } else if (vehicleType.trim().equalsIgnoreCase("car")) {
            Car carToBeParked = carRepository.findById(vehicleId).orElseThrow(
                    () -> new ResourceNotFoundException("We do not have this vehicle"));

            CarPark carPark = carParkRepository.findByName(facilityName).orElseThrow(
                    () -> new ResourceNotFoundException("We do not have this facility"));

            if (!carToBeParked.getCity().getName().equalsIgnoreCase(carPark.getCity().getName())) {
                throw new IllegalArgumentException("The vehicle and the facility must be in the same city");
            }

            carToBeParked.setIsParked(true);
            carToBeParked.setParkingFacility(carPark);
            carRepository.save(carToBeParked);
        } else {
            throw new IllegalArgumentException("We do not support this vehicle");
        }

        return "Vehicle with id: " + vehicleId + " was parked in " + facilityName;
    }

    public String unParkVehicle(String vehicleType, Integer vehicleId) {
        if (vehicleType.trim().equalsIgnoreCase("bike")) {
            Bike bikeToBeUnParked = bikeRepository.findById(vehicleId).orElseThrow(
                    () -> new ResourceNotFoundException("We do not have this vehicle"));


            bikeToBeUnParked.setIsParked(false);
            bikeToBeUnParked.setParkingFacility(null);
            bikeRepository.save(bikeToBeUnParked);
        } else if (vehicleType.trim().equalsIgnoreCase("car")) {
            Car carToBeUnParked = carRepository.findById(vehicleId).orElseThrow(
                    () -> new ResourceNotFoundException("We do not have this vehicle"));


            carToBeUnParked.setIsParked(true);
            carToBeUnParked.setParkingFacility(null);
            carRepository.save(carToBeUnParked);
        } else {
            throw new IllegalArgumentException("We do not support this vehicle");
        }

        return "Vehicle with id: " + vehicleId + " was unparked";

    }
}
