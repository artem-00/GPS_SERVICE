package com.example.gps.service;

import com.example.gps.entity.LocationEntity;
import com.example.gps.entity.UserEntity;
import com.example.gps.exception.LocationNotFoundException;
import com.example.gps.model.Location;
import com.example.gps.model.LocationInfo;
import com.example.gps.repository.LocationRepository;
import com.example.gps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepo;

    @Autowired
    private UserRepository userRepo;

    public LocationEntity saveLocation(LocationEntity locationEntity) {
        return locationRepo.save(locationEntity);
    }

    public Location foundLocation(LocationEntity location, Long id) {
        UserEntity user = userRepo.findById(id).orElse(null);
        if (user != null) {
            location.setUser(user);
            return Location.toModel(locationRepo.save(location));
        }
        return null;
    }

    public List<LocationInfo> getUserLocations(Long userId) {
        List<LocationEntity> locations = locationRepo.findAllByUserId(userId);
        return locations.stream()
                .map(locationEntity -> {
                    LocationInfo locationInfo = new LocationInfo();
                    locationInfo.setCountry(locationEntity.getCountry());
                    locationInfo.setCity(locationEntity.getCity());
                    return locationInfo;
                })
                .collect(Collectors.toList());
    }

    public Location getLocation(Long id) throws LocationNotFoundException {
        LocationEntity location = locationRepo.findById(id).get();
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
