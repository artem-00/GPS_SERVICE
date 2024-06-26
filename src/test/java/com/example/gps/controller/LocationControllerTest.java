package com.example.gps.controller;

import com.example.gps.DTO.LocationDTO;
import com.example.gps.cache.LocationCache;
import com.example.gps.entity.Location;
import com.example.gps.exception.EndpointActionLogger;
import com.example.gps.exception.LocationNotFoundException;
import com.example.gps.service.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationControllerTest {

    @Mock
    private LocationService locationService;
    @Mock
    private EndpointActionLogger endpointActionLogger;
    @Mock
    private LocationCache locationCache;

    @InjectMocks
    private LocationController locationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getLocationById_LocationFound() throws LocationNotFoundException {
        Long locationId = 1L;
        LocationDTO locationDTO = new LocationDTO();
        when(locationService.getLocation(locationId)).thenReturn(locationDTO);

        ResponseEntity<LocationDTO> response = locationController.getLocationById(locationId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(locationDTO, response.getBody());
        verify(locationService, times(1)).getLocation(locationId);
    }

    @Test
    void getLocationById_LocationNotFound() throws LocationNotFoundException {
        Long locationId = 1L;
        when(locationService.getLocation(locationId)).thenThrow(new LocationNotFoundException("Location not found"));

        ResponseEntity<LocationDTO> response = locationController.getLocationById(locationId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(locationService, times(1)).getLocation(locationId);
    }

    @Test
    void deleteLocation_Success() throws LocationNotFoundException {
        Long locationId = 1L;

        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Location deleted");

        doNothing().when(locationService).deleteLocationById(locationId);

        ResponseEntity<String> response = locationController.deleteLocation(locationId);

        assertEquals(expectedResponse, response);
        verify(locationService, times(1)).deleteLocationById(locationId);
        verify(endpointActionLogger, times(1)).logDeleteLocation(locationId);
    }

    @Test
    void deleteLocation_LocationNotFound() throws LocationNotFoundException {
        Long locationId = 1L;

        doThrow(new RuntimeException("Location not found")).when(locationService).deleteLocationById(locationId);

        ResponseEntity<String> response = locationController.deleteLocation(locationId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Произошла ошибка", response.getBody());
        verify(locationService, times(1)).deleteLocationById(locationId);
        verify(endpointActionLogger, never()).logDeleteLocation(locationId);
    }


    @Test
    void updateLocation_LocationFound() throws LocationNotFoundException {
        Long locationId = 1L;
        Location updatedLocation = new Location();
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Location updated");

        doNothing().when(locationService).updateLocation(locationId, updatedLocation);

        ResponseEntity<String> response = locationController.updateLocation(locationId, updatedLocation);

        assertEquals(expectedResponse, response);
        verify(locationService, times(1)).updateLocation(locationId, updatedLocation);
    }

    @Test
    void updateLocation_LocationNotFound() throws LocationNotFoundException {
        Long locationId = 1L;
        Location updatedLocation = new Location();

        doThrow(new LocationNotFoundException("Location not found")).when(locationService).updateLocation(locationId, updatedLocation);

        ResponseEntity<String> response = locationController.updateLocation(locationId, updatedLocation);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Location not found", response.getBody());
        verify(locationService, times(1)).updateLocation(locationId, updatedLocation);
    }

    @Test
    void updateLocation_OtherException() throws LocationNotFoundException {
        Long locationId = 1L;
        Location updatedLocation = new Location();

        doThrow(new RuntimeException("Other exception")).when(locationService).updateLocation(locationId, updatedLocation);

        ResponseEntity<String> response = locationController.updateLocation(locationId, updatedLocation);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Произошла ошибка при обновлении местоположения", response.getBody());
        verify(locationService, times(1)).updateLocation(locationId, updatedLocation);
    }

    @Test
    void getAllLocations_Success() {
        List<LocationDTO> expectedLocations = new ArrayList<>();
        expectedLocations.add(new LocationDTO());
        expectedLocations.add(new LocationDTO());

        when(locationService.getAllLocations()).thenReturn(expectedLocations);

        ResponseEntity<List<LocationDTO>> response = locationController.getAllLocations();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedLocations, response.getBody());
        verify(locationService, times(1)).getAllLocations();
    }

    @Test
    void getAllLocations_Exception() {
        when(locationService.getAllLocations()).thenThrow(new RuntimeException("An error occurred"));

        ResponseEntity<List<LocationDTO>> response = locationController.getAllLocations();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(locationService, times(1)).getAllLocations();
    }

    @Test
    void printCache() {
        locationController.printCache();

        verify(locationCache, times(1)).logCache();
    }

    @Test
    void testFoundLocation_CachedLocation() {
        Long userId = 1L;
        String ip = "127.0.0.1";
        Location cachedLocation = new Location();

        when(locationCache.getFromCache(any(Location.class))).thenReturn(cachedLocation);

        ResponseEntity<String> response = locationController.foundLocation(userId, ip);

        assertEquals("Location found in cache: " + cachedLocation.toString(), response.getBody());

        verify(locationCache, times(1)).getFromCache(any(Location.class));
        verify(locationService, never()).saveLocation(any(Location.class));
        verify(locationCache, never()).addToCache(any(Location.class));
        verify(endpointActionLogger, never()).logAddLocation(anyString());
    }
}