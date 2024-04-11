package com.example.gps.service;

import com.example.gps.entity.Location;
import com.example.gps.exception.LocationNotFoundException;
import com.example.gps.DTO.LocationDTO;
import com.example.gps.repository.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService {


    private LocationRepository locationRepo;

    public LocationService(LocationRepository locationRepo) {
        this.locationRepo = locationRepo;
    }

    public Location saveLocation(Location locationEntity) {
        return locationRepo.save(locationEntity);
    }

    public LocationDTO getLocation(Long id) throws LocationNotFoundException {
        Location location = locationRepo.findById(id).orElse(null);
        if (location == null){
            throw new LocationNotFoundException("Location not found");
        }
        return LocationDTO.toModel(location);
    }

    public void deleteLocationById(Long id){
        locationRepo.deleteById(id);
    }

    public void updateLocation(Long id, Location updatedLocation) throws LocationNotFoundException {
        Location location = locationRepo.findById(id).orElse(null);
        if (location == null) {
            throw new LocationNotFoundException("Location not found");
        }
        location.setCountry(updatedLocation.getCountry());
        location.setCity(updatedLocation.getCity());

        locationRepo.save(location);
    }
}
