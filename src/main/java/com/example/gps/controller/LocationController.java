package com.example.gps.controller;

import com.example.gps.entity.HistoryEntity;
import com.example.gps.entity.LocationEntity;
import com.example.gps.entity.UserEntity;
import com.example.gps.model.LocationInfo;
import com.example.gps.service.LocationService;
import com.example.gps.repository.HistoryRepository;
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
    private HistoryRepository historyRepository;

    private final String IPINFO_API_URL = "https://ipinfo.io/%s/json";
    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public ResponseEntity foundLocation(@RequestParam Long userId, @RequestParam String ip) {
        try {
            String apiUrl = String.format(IPINFO_API_URL, ip);
            LocationInfo locationInfo = restTemplate.getForObject(apiUrl, LocationInfo.class);

            LocationEntity locationEntity = new LocationEntity();
            locationEntity.setCountry(locationInfo.getCountry());
            locationEntity.setCity(locationInfo.getCity());

            UserEntity userEntity = new UserEntity();
            userEntity.setId(userId);

            locationEntity.setUser(userEntity);

            // Сохраняем местоположение в базу данных
            LocationEntity savedLocation = locationService.saveLocation(locationEntity);

            // Создаем запись истории
            HistoryEntity history = new HistoryEntity();
            history.setIdUser(userId);
            history.setIdLocation(savedLocation.getId());
            history.setIp(ip);
            history.setRequestDateTime(new Date());

            // Сохраняем запись истории в базу данных
            historyRepository.save(history);

            return ResponseEntity.ok("Location saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occurred while saving location.");
        }
    }


}
