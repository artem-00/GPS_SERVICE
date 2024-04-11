package com.example.gps.service;

import com.example.gps.entity.LocationEntity;
import com.example.gps.exception.LocationNotFoundException;
import com.example.gps.model.Location;
import com.example.gps.model.LocationInfo;
import com.example.gps.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepo;

    public LocationEntity saveLocation(LocationEntity locationEntity) {
        return locationRepo.save(locationEntity);
    }

    public Location getLocation(Long id) throws LocationNotFoundException {
        LocationEntity location = locationRepo.findById(id).orElse(null);
        if (location == null){
            throw new LocationNotFoundException("Location not found");
        }
        return Location.toModel(location);
    }

    public Long deleteLocationById(Long id){
        locationRepo.deleteById(id);
        return id;
    }

    public void updateLocation(Long id, LocationEntity updatedLocation) throws LocationNotFoundException {
        LocationEntity location = locationRepo.findById(id).orElse(null);
        if (location == null) {
            throw new LocationNotFoundException("Location not found");
        }
        location.setCountry(updatedLocation.getCountry());
        location.setCity(updatedLocation.getCity());

        locationRepo.save(location);
    }
}
