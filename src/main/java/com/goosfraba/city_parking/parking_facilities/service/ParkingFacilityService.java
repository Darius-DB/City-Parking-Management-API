package com.goosfraba.city_parking.parking_facilities.service;

import com.goosfraba.city_parking.cities.model.City;
import com.goosfraba.city_parking.cities.repository.CityRepository;
import com.goosfraba.city_parking.cities.service.CityService;
import com.goosfraba.city_parking.exceptions.ResourceAlreadyPresentException;
import com.goosfraba.city_parking.exceptions.ResourceNotFoundException;
import com.goosfraba.city_parking.parking_facilities.dto.ParkingFacilityDto;
import com.goosfraba.city_parking.parking_facilities.dto.ParkingFacilityMapper;
import com.goosfraba.city_parking.parking_facilities.model.BikeRack;
import com.goosfraba.city_parking.parking_facilities.model.CarPark;
import com.goosfraba.city_parking.parking_facilities.model.ParkingFacility;
import com.goosfraba.city_parking.parking_facilities.repository.BikeRackRepository;
import com.goosfraba.city_parking.parking_facilities.repository.CarParkRepository;
import com.goosfraba.city_parking.utils.InputFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingFacilityService {

    private final BikeRackRepository bikeRackRepository;
    private final CarParkRepository carParkRepository;
    private final CityRepository cityRepository;
    private final CityService cityService;




    public ParkingFacilityService(BikeRackRepository bikeRackRepository, CarParkRepository carParkRepository, CityRepository cityRepository, CityService cityService) {
        this.bikeRackRepository = bikeRackRepository;
        this.carParkRepository = carParkRepository;

        this.cityRepository = cityRepository;
        this.cityService = cityService;
    }



    @Transactional
    public ParkingFacilityDto createFacility(ParkingFacilityDto facilityDto, String facilityType) {
        ParkingFacility parkingFacilityToSave = ParkingFacilityMapper.toFacility(facilityDto);
        City requestedCity = cityService.getCityByName(facilityDto.getCity());


        if (facilityType.trim().equalsIgnoreCase("bike")) {
            if (bikeRackRepository.findByName(parkingFacilityToSave.getName()).isPresent()) {
                throw new ResourceAlreadyPresentException(parkingFacilityToSave.getName() +
                        " already exists in our database");
            }

            parkingFacilityToSave.setCity(requestedCity);
            BikeRack bikeRackToSave = new BikeRack();
            BeanUtils.copyProperties(parkingFacilityToSave,bikeRackToSave);
            bikeRackRepository.save(bikeRackToSave);
        } else if (facilityType.trim().equalsIgnoreCase("car")) {
            if (carParkRepository.findByName(parkingFacilityToSave.getName()).isPresent()) {
                throw new ResourceAlreadyPresentException(parkingFacilityToSave.getName() +
                        " already exists in our database");
            }

            parkingFacilityToSave.setCity(requestedCity);
            CarPark carParkToSave = new CarPark();
            BeanUtils.copyProperties(parkingFacilityToSave,carParkToSave);
            carParkRepository.save(carParkToSave);
        } else {
            throw new IllegalArgumentException("We do not offer this type of facility");
        }

        return ParkingFacilityMapper.toFacilityDto(parkingFacilityToSave);
    }

    public ParkingFacilityDto getFacilityById(Integer id, String facilityType) {
        ParkingFacilityDto parkingFacility = new ParkingFacilityDto();

        if (facilityType.trim().equalsIgnoreCase("bike")) {
            parkingFacility = ParkingFacilityMapper.toFacilityDto(
                    bikeRackRepository.findById(id).orElseThrow(
                            () -> new ResourceNotFoundException("There is no facility with this id")));

        } else if (facilityType.trim().equalsIgnoreCase("car")) {
            parkingFacility = ParkingFacilityMapper.toFacilityDto(
                    carParkRepository.findById(id).orElseThrow(
                            () -> new ResourceNotFoundException("There is no facility with this id")));
        } else {
            throw new IllegalArgumentException("We do not offer this type of facility");
        }

        return parkingFacility;

    }

    public List<ParkingFacilityDto> getAllFacilitiesForAGivenCity(String city) {
        List<ParkingFacilityDto> facilities = new ArrayList<>();
        String formattedCityName = InputFormatter.formatCityName(city);

        facilities.addAll(bikeRackRepository.findAllByCityName(formattedCityName).stream().map(
                ParkingFacilityMapper::toFacilityDto).collect(Collectors.toList()));
        facilities.addAll(carParkRepository.findAllByCityName(formattedCityName).stream().map(
                ParkingFacilityMapper::toFacilityDto).collect(Collectors.toList()));

        return facilities;

    }
}
