package com.example.gps.DTO;

import com.example.gps.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    @Test
    void toModel() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setLogin("testUser");

        // Act
        UserDTO userDTO = UserDTO.toModel(user);

        // Assert
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getLogin(), userDTO.getLogin());
    }

    @Test
    void getId() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        Long id = 1L;
        userDTO.setId(id);

        // Act & Assert
        assertEquals(id, userDTO.getId());
    }

    @Test
    void setId() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        Long id = 1L;

        // Act
        userDTO.setId(id);

        // Assert
        assertEquals(id, userDTO.getId());
    }

    @Test
    void getLogin() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        String login = "testUser";
        userDTO.setLogin(login);

        // Act & Assert
        assertEquals(login, userDTO.getLogin());
    }

    @Test
    void setLogin() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        String login = "testUser";

        // Act
        userDTO.setLogin(login);

        // Assert
        assertEquals(login, userDTO.getLogin());
    }

    @Test
    void getLocations() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        List<LocationDTO> locations = new ArrayList<>();
        locations.add(new LocationDTO());
        userDTO.setLocations(locations);

        // Act & Assert
        assertEquals(locations, userDTO.getLocations());
    }

    @Test
    void setLocations() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        List<LocationDTO> locations = new ArrayList<>();

        // Act
        userDTO.setLocations(locations);

        // Assert
        assertEquals(locations, userDTO.getLocations());
    }
}