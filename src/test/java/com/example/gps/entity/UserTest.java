package com.example.gps.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getId() {
        // Arrange
        Long id = 1L;
        User user = new User();
        user.setId(id);

        // Act & Assert
        assertEquals(id, user.getId());
    }

    @Test
    void setId() {
        // Arrange
        Long id = 1L;
        User user = new User();

        // Act
        user.setId(id);

        // Assert
        assertEquals(id, user.getId());
    }

    @Test
    void getLogin() {
        // Arrange
        String login = "testLogin";
        User user = new User();
        user.setLogin(login);

        // Act & Assert
        assertEquals(login, user.getLogin());
    }

    @Test
    void setLogin() {
        // Arrange
        String login = "testLogin";
        User user = new User();

        // Act
        user.setLogin(login);

        // Assert
        assertEquals(login, user.getLogin());
    }

    @Test
    void getPassword() {
        // Arrange
        String password = "testPassword";
        User user = new User();
        user.setPassword(password);

        // Act & Assert
        assertEquals(password, user.getPassword());
    }

    @Test
    void setPassword() {
        // Arrange
        String password = "testPassword";
        User user = new User();

        // Act
        user.setPassword(password);

        // Assert
        assertEquals(password, user.getPassword());
    }

    @Test
    void getLocation() {
        // Arrange
        List<Location> locations = new ArrayList<>();
        locations.add(new Location());
        User user = new User();
        user.setLocation(locations);

        // Act & Assert
        assertEquals(locations, user.getLocation());
    }

    @Test
    void setLocation() {
        // Arrange
        List<Location> locations = new ArrayList<>();
        locations.add(new Location());
        User user = new User();

        // Act
        user.setLocation(locations);

        // Assert
        assertEquals(locations, user.getLocation());
    }
}
