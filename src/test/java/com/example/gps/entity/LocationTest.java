package com.example.gps.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LocationTest {

    @Test
    void getId() {
        Long id = 1L;
        Location location = new Location();
        location.setId(id);

        assertEquals(id, location.getId());
    }

    @Test
    void setId() {
        Long id = 1L;
        Location location = new Location();

        location.setId(id);

        assertEquals(id, location.getId());
    }

    @Test
    void getCountry() {
        String country = "TestCountry";
        Location location = new Location();
        location.setCountry(country);

        assertEquals(country, location.getCountry());
    }

    @Test
    void setCountry() {
        String country = "TestCountry";
        Location location = new Location();

        location.setCountry(country);

        assertEquals(country, location.getCountry());
    }

    @Test
    void getCity() {
        String city = "TestCity";
        Location location = new Location();
        location.setCity(city);

        assertEquals(city, location.getCity());
    }

    @Test
    void setCity() {
        String city = "TestCity";
        Location location = new Location();

        location.setCity(city);

        assertEquals(city, location.getCity());
    }

    @Test
    void getUser() {
        User user = new User();
        Location location = new Location();
        location.setUser(user);

        assertEquals(user, location.getUser());
    }

    @Test
    void setUser() {
        User user = new User();
        Location location = new Location();

        location.setUser(user);

        assertEquals(user, location.getUser());
    }

    @Test
    void testToString() {
        Location location = new Location();
        location.setId(1L);
        location.setCity("TestCity");
        location.setCountry("TestCountry");

        assertEquals("id = 1, city = TestCity, country = TestCountry", location.toString());
    }

    @Test
    void testEquals() {
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

        assertEquals(location1, location2);
        assertNotEquals(location1, location3);
    }

    @Test
    void testHashCode() {
        Location location1 = new Location();
        location1.setId(1L);
        location1.setCity("TestCity");
        location1.setCountry("TestCountry");

        Location location2 = new Location();
        location2.setId(1L);
        location2.setCity("TestCity");
        location2.setCountry("TestCountry");

        assertEquals(location1.hashCode(), location2.hashCode());
    }
}
