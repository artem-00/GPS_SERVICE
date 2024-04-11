package com.example.gps.controller;

import com.example.gps.entity.HistoryEntity;
import com.example.gps.entity.LocationEntity;
import com.example.gps.entity.UserEntity;
import com.example.gps.exception.LocationNotFoundException;
import com.example.gps.model.Location;
import com.example.gps.model.LocationInfo;
import com.example.gps.service.LocationService;
import com.example.gps.repository.HistoryRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    private HistoryRepository historyRepository;

    private final String IpinfoApiURL = "https://ipinfo.io/%s/json";
    private final RestTemplate restTemplate = new RestTemplate();

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/userId/{userId}/ip/{ip}")
    public final ResponseEntity<String> foundLocation(@PathVariable Long userId, @PathVariable String ip) {
        try {
            String apiUrl = String.format(IpinfoApiURL, ip);

            // 1. Проверяем, что полученный объект не равен null
            LocationInfo locationInfo = restTemplate.getForObject(apiUrl, LocationInfo.class);
            if (locationInfo != null) {
                LocationEntity locationEntity = new LocationEntity();
                locationEntity.setCountry(locationInfo.getCountry());
                locationEntity.setCity(locationInfo.getCity());
                UserEntity userEntity = new UserEntity();
                userEntity.setId(userId);
                locationEntity.setUser(userEntity);
                // Сохраняем местоположение в базу данных
                LocationEntity savedLocation = locationService.saveLocation(locationEntity);

                // 2. Возвращаем сообщение об успешном сохранении местоположения
                return ResponseEntity.ok("Местоположение сохранено");
            } else {
                // 3. Возвращаем сообщение об ошибке, если locationInfo равен null
                return ResponseEntity.badRequest().body("Ошибка получения информации о местоположении");
            }
        } catch (Exception e) {
            // 4. Возвращаем сообщение об ошибке при возникновении исключения
            return ResponseEntity.badRequest().body("Произошла ошибка при обработке запроса");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        try {
            Location location = locationService.getLocation(id);
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
    public ResponseEntity<String> updateLocation(@PathVariable Long id, @RequestBody LocationEntity updatedLocation) {
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

