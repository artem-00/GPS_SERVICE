package com.example.gps.DTO;

import com.example.gps.entity.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationDTOTest {

    @Test
    void toModel() {
        // Arrange
        Location location = new Location();
        location.setId(1L);
        location.setCountry("Country");
        location.setCity("City");

        // Act
        LocationDTO locationDTO = LocationDTO.toModel(location);

        // Assert
        assertEquals(location.getId(), locationDTO.getId());
        assertEquals(location.getCountry(), locationDTO.getCountry());
        assertEquals(location.getCity(), locationDTO.getCity());
    }

    @Test
    void getId() {
        // Arrange
        LocationDTO locationDTO = new LocationDTO();
        Long id = 1L;
        locationDTO.setId(id);

        // Act & Assert
        assertEquals(id, locationDTO.getId());
    }

    @Test
    void setId() {
        // Arrange
        LocationDTO locationDTO = new LocationDTO();
        Long id = 1L;

        // Act
        locationDTO.setId(id);

        // Assert
        assertEquals(id, locationDTO.getId());
    }


    @Test
    void getCountry() {
        // Arrange
        LocationDTO locationDTO = new LocationDTO();
        String country = "Country";
        locationDTO.setCountry(country);

        // Act & Assert
        assertEquals(country, locationDTO.getCountry());
    }

    @Test
    void setCountry() {
        // Arrange
        LocationDTO locationDTO = new LocationDTO();
        String country = "Test Country";

        // Act
        locationDTO.setCountry(country);

        // Assert
        assertEquals(country, locationDTO.getCountry());
    }

    @Test
    void getCity() {
        // Arrange
        LocationDTO locationDTO = new LocationDTO();
        String city = "City";
        locationDTO.setCity(city);

        // Act & Assert
        assertEquals(city, locationDTO.getCity());
    }



    @Test
    void setCity() {
        // Arrange
        LocationDTO locationDTO = new LocationDTO();
        String city = "Test City";

        // Act
        locationDTO.setCity(city);

        // Assert
        assertEquals(city, locationDTO.getCity());
    }
}