package com.example.gps.DTO;

import com.example.gps.entity.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationDTOTest {

    @Test
    void toModel() {
        Location location = new Location();
        location.setId(1L);
        location.setCountry("Country");
        location.setCity("City");

        LocationDTO locationDTO = LocationDTO.toModel(location);

        assertEquals(location.getId(), locationDTO.getId());
        assertEquals(location.getCountry(), locationDTO.getCountry());
        assertEquals(location.getCity(), locationDTO.getCity());
    }

    @Test
    void getId() {
        LocationDTO locationDTO = new LocationDTO();
        Long id = 1L;
        locationDTO.setId(id);

        assertEquals(id, locationDTO.getId());
    }

    @Test
    void setId() {
        LocationDTO locationDTO = new LocationDTO();
        Long id = 1L;

        locationDTO.setId(id);

        assertEquals(id, locationDTO.getId());
    }


    @Test
    void getCountry() {
        LocationDTO locationDTO = new LocationDTO();
        String country = "Country";
        locationDTO.setCountry(country);

        assertEquals(country, locationDTO.getCountry());
    }

    @Test
    void setCountry() {
        LocationDTO locationDTO = new LocationDTO();
        String country = "Test Country";

        locationDTO.setCountry(country);

        assertEquals(country, locationDTO.getCountry());
    }

    @Test
    void getCity() {
        LocationDTO locationDTO = new LocationDTO();
        String city = "City";
        locationDTO.setCity(city);

        assertEquals(city, locationDTO.getCity());
    }

    @Test
    void setCity() {
        LocationDTO locationDTO = new LocationDTO();
        String city = "Test City";

        locationDTO.setCity(city);

        assertEquals(city, locationDTO.getCity());
    }
}