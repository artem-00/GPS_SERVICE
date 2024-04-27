package com.example.gps.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LocationTest {

    @Test
    void getId() {
        // Arrange
        Long id = 1L;
        Location location = new Location();
        location.setId(id);

        // Act & Assert
        assertEquals(id, location.getId());
    }

    @Test
    void setId() {
        // Arrange
        Long id = 1L;
        Location location = new Location();

        // Act
        location.setId(id);

        // Assert
        assertEquals(id, location.getId());
    }

    @Test
    void getCountry() {
        // Arrange
        String country = "TestCountry";
        Location location = new Location();
        location.setCountry(country);

        // Act & Assert
        assertEquals(country, location.getCountry());
    }

    @Test
    void setCountry() {
        // Arrange
        String country = "TestCountry";
        Location location = new Location();

        // Act
        location.setCountry(country);

        // Assert
        assertEquals(country, location.getCountry());
    }

    @Test
    void getCity() {
        // Arrange
        String city = "TestCity";
        Location location = new Location();
        location.setCity(city);

        // Act & Assert
        assertEquals(city, location.getCity());
    }

    @Test
    void setCity() {
        // Arrange
        String city = "TestCity";
        Location location = new Location();

        // Act
        location.setCity(city);

        // Assert
        assertEquals(city, location.getCity());
    }

    @Test
    void getUser() {
        // Arrange
        User user = new User();
        Location location = new Location();
        location.setUser(user);

        // Act & Assert
        assertEquals(user, location.getUser());
    }

    @Test
    void setUser() {
        // Arrange
        User user = new User();
        Location location = new Location();

        // Act
        location.setUser(user);

        // Assert
        assertEquals(user, location.getUser());
    }

    @Test
    void testToString() {
        // Arrange
        Location location = new Location();
        location.setId(1L);
        location.setCity("TestCity");
        location.setCountry("TestCountry");

        // Act & Assert
        assertEquals("id = 1, city = TestCity, country = TestCountry", location.toString());
    }

    @Test
    void testEquals() {
        // Arrange
        Location location1 = new Location();
        location1.setId(1L);
        location1.setCity("TestCity");
        location1.setCountry("TestCountry");

        Location location2 = new Location();
        location2.setId(1L);
        location2.setCity("TestCity");
        location2.setCountry("TestCountry");

        Location location3 = new Location();
        location3.setId(2L);
        location3.setCity("AnotherCity");
        location3.setCountry("AnotherCountry");

        // Act & Assert
        assertEquals(location1, location2);
        assertNotEquals(location1, location3);
    }

    @Test
    void testHashCode() {
        // Arrange
        Location location1 = new Location();
        location1.setId(1L);
        location1.setCity("TestCity");
        location1.setCountry("TestCountry");

        Location location2 = new Location();
        location2.setId(1L);
        location2.setCity("TestCity");
        location2.setCountry("TestCountry");

        // Act & Assert
        assertEquals(location1.hashCode(), location2.hashCode());
    }
}
