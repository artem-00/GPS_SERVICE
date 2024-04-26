package com.example.gps.controller;

import com.example.gps.entity.History;
import com.example.gps.entity.Location;
import com.example.gps.service.LocationService;
import com.example.gps.entity.User;
import com.example.gps.exception.LocationNotFoundException;
import com.example.gps.DTO.LocationDTO;
import  com.example.gps.repository.HistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private  HistoryRepository historyRepo;
    private final String ipinfoApiURL = "https://ipinfo.io/%s/json";
    private final RestTemplate restTemplate = new RestTemplate();

    public LocationController() {
    }

    @PostMapping("/userId/{userId}/ip/{ip}")
    public ResponseEntity<String> foundLocation(@PathVariable Long userId, @PathVariable String ip) {
        try {
            String apiUrl = String.format(ipinfoApiURL, ip);
            LocationDTO locationDTO = restTemplate.getForObject(apiUrl, LocationDTO.class);

            Location location = new Location();
            assert locationDTO != null;
            location.setCountry(locationDTO.getCountry());
            location.setCity(locationDTO.getCity());

            User user = new User();
            user.setId(userId);
            location.setUser(user);
            // Сохраняем местоположение в базу данных
            Location savedLocation = locationService.saveLocation(location);
            // Создаем запись истории
            History history = new History();
            history.setIdUser(userId);
            history.setIdLocation(savedLocation.getId());
            history.setIp(ip);
            history.setRequestDateTime(new Date());
            // Сохраняем запись истории в базу данных
            historyRepo.save(history);
            return ResponseEntity.ok("Location saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occurred while saving location.");
        }
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
}

