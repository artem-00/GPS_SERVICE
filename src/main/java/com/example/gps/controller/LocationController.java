package com.example.gps.controller;

import com.example.gps.entity.History;
import com.example.gps.entity.Location;
import com.example.gps.service.LocationService;
import com.example.gps.entity.User;
import com.example.gps.exception.LocationNotFoundException;
import com.example.gps.DTO.LocationDTO;
import  com.example.gps.repository.HistoryRepository;
import com.example.gps.cache.LocationCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private  HistoryRepository historyRepo;
    @Autowired
    private LocationCache locationCache;
    private final String ipinfoApiURL = "https://ipinfo.io/%s/json";
    private final RestTemplate restTemplate = new RestTemplate();

    public LocationController() {
    }

    @PostMapping("/userId/{userId}/ip/{ip}")
    public ResponseEntity<String> foundLocation(@PathVariable Long userId, @PathVariable String ip) {
            String apiUrl = String.format(ipinfoApiURL, ip);
            LocationDTO locationDTO = restTemplate.getForObject(apiUrl, LocationDTO.class);

            Location location = new Location();
            assert locationDTO != null;
            location.setCountry(locationDTO.getCountry());
            location.setCity(locationDTO.getCity());

            User user = new User();
            user.setId(userId);
            location.setUser(user);

            //добавил
            Location cachedLocation = locationCache.getFromCache(location);
            if (cachedLocation != null) {
                return ResponseEntity.ok("Location found in cache: " + cachedLocation);
            }

            Location savedLocation = locationService.saveLocation(location);
            locationCache.addToCache(savedLocation);


            return ResponseEntity.ok("Location saved successfully.");
    }
    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getLocationById(@PathVariable Long id) {
        try {
            LocationDTO location = locationService.getLocation(id);
            return ResponseEntity.ok(location);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id) {
        try {
            locationService.deleteLocationById(id);
            return ResponseEntity.ok("Location deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLocation(@PathVariable Long id, @RequestBody Location updatedLocation) {
        try {
            locationService.updateLocation(id, updatedLocation);
            return ResponseEntity.ok("Location updated");
        } catch (LocationNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка при обновлении местоположения");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        try {
            List<LocationDTO> locations = (List<LocationDTO>) locationService.getAllLocations();
            return ResponseEntity.ok(locations);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/cache")
    public void printCache() {
        locationCache.logCache();
    }
}

