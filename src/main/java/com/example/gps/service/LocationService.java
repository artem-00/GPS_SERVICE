package com.example.gps.service;

import com.example.gps.entity.LocationEntity;
import com.example.gps.entity.UserEntity;
import com.example.gps.model.Location;
import com.example.gps.repository.LocationRepository;
import com.example.gps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
