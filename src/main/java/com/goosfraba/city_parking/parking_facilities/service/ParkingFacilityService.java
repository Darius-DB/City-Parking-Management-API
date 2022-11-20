package com.goosfraba.city_parking.parking_facilities.service;

import com.goosfraba.city_parking.cities.model.City;
import com.goosfraba.city_parking.cities.repository.CityRepository;
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

@Service
public class ParkingFacilityService {

    private final BikeRackRepository bikeRackRepository;
    private final CarParkRepository carParkRepository;
    private final CityRepository cityRepository;




    public ParkingFacilityService(BikeRackRepository bikeRackRepository, CarParkRepository carParkRepository, CityRepository cityRepository) {
        this.bikeRackRepository = bikeRackRepository;
        this.carParkRepository = carParkRepository;

        this.cityRepository = cityRepository;
    }



    @Transactional
    public ParkingFacilityDto createFacility(ParkingFacilityDto facilityDto, String facilityType) {
        ParkingFacility parkingFacilityToSave = ParkingFacilityMapper.toFacility(facilityDto);
        City requestedCity = cityRepository.findByName(InputFormatter.formatCityName(facilityDto.getCity())).orElseThrow(
                () -> new ResourceNotFoundException("No such city")
        );


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
}
