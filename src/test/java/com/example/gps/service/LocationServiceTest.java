package com.example.gps.service;

import com.example.gps.DTO.LocationDTO;
import com.example.gps.entity.Location;
import com.example.gps.exception.LocationNotFoundException;
import com.example.gps.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    void saveLocation() {
        Location location = new Location();
        location.setCountry("TestCountry");
        location.setCity("TestCity");

        when(locationRepository.save(location)).thenReturn(location);

        Location savedLocation = locationService.saveLocation(location);

        verify(locationRepository, times(1)).save(location);
        assertEquals(location, savedLocation);
    }


    @Test
    void getLocation_LocationFound() throws LocationNotFoundException {
        long locationId = 1L;
        Location location = new Location();
        location.setId(locationId);
        location.setCountry("TestCountry");
        location.setCity("TestCity");

        when(locationRepository.findById(locationId)).thenReturn(Optional.of(location));

        LocationDTO locationDTO = locationService.getLocation(locationId);

        assertEquals(location.getCountry(), locationDTO.getCountry());
        assertEquals(location.getCity(), locationDTO.getCity());
    }

    @Test
    void getLocation_LocationNotFound() {
        long locationId = 1L;
        when(locationRepository.findById(locationId)).thenReturn(Optional.empty());

        assertThrows(LocationNotFoundException.class, () -> {
            locationService.getLocation(locationId);
        });
    }

    @Test
    void deleteLocationById() {
        Long locationId = 1L;

        locationService.deleteLocationById(locationId);

        verify(locationRepository, times(1)).deleteById(locationId);
    }

    @Test
    void updateLocation_LocationExists() throws LocationNotFoundException {
        Long locationId = 1L;
        Location location = new Location();
        location.setId(locationId);
        location.setCountry("TestCountry");
        location.setCity("TestCity");

        Location updatedLocation = new Location();
        updatedLocation.setCountry("UpdatedCountry");
        updatedLocation.setCity("UpdatedCity");

        when(locationRepository.findById(locationId)).thenReturn(Optional.of(location));

        locationService.updateLocation(locationId, updatedLocation);

        verify(locationRepository, times(1)).findById(locationId);
        verify(locationRepository, times(1)).save(location);
        assertEquals(updatedLocation.getCountry(), location.getCountry());
        assertEquals(updatedLocation.getCity(), location.getCity());
    }

    @Test
    void updateLocation_LocationNotFound() {
        Long locationId = 1L;
        Location updatedLocation = new Location();
        updatedLocation.setCountry("UpdatedCountry");
        updatedLocation.setCity("UpdatedCity");

        when(locationRepository.findById(locationId)).thenReturn(Optional.empty());

        assertThrows(LocationNotFoundException.class, () -> {
            locationService.updateLocation(locationId, updatedLocation);
        });
    }

    @Test
    void getAllLocations() {
        Location location1 = new Location();
        location1.setCountry("Country1");
        location1.setCity("City1");

        Location location2 = new Location();
        location2.setCountry("Country2");
        location2.setCity("City2");

        when(locationRepository.findAll()).thenReturn(Arrays.asList(location1, location2));

        Iterable<LocationDTO> result = locationService.getAllLocations();

        List<LocationDTO> resultList = (List<LocationDTO>) result;
        assertEquals(2, resultList.size());
        assertEquals("Country1", resultList.get(0).getCountry());
        assertEquals("City1", resultList.get(0).getCity());
        assertEquals("Country2", resultList.get(1).getCountry());
        assertEquals("City2", resultList.get(1).getCity());
    }
}