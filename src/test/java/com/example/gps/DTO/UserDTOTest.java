package com.example.gps.DTO;

import com.example.gps.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    @Test
    void toModel() {
        User user = new User();
        user.setId(1L);
        user.setLogin("testUser");

        UserDTO userDTO = UserDTO.toModel(user);

        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getLogin(), userDTO.getLogin());
    }

    @Test
    void getId() {
        UserDTO userDTO = new UserDTO();
        Long id = 1L;
        userDTO.setId(id);

        assertEquals(id, userDTO.getId());
    }

    @Test
    void setId() {
        UserDTO userDTO = new UserDTO();
        Long id = 1L;

        userDTO.setId(id);

        assertEquals(id, userDTO.getId());
    }

    @Test
    void getLogin() {
        UserDTO userDTO = new UserDTO();
        String login = "testUser";
        userDTO.setLogin(login);

        assertEquals(login, userDTO.getLogin());
    }

    @Test
    void setLogin() {
        UserDTO userDTO = new UserDTO();
        String login = "testUser";

        userDTO.setLogin(login);

        assertEquals(login, userDTO.getLogin());
    }

    @Test
    void getLocations() {
        UserDTO userDTO = new UserDTO();
        List<LocationDTO> locations = new ArrayList<>();
        locations.add(new LocationDTO());
        userDTO.setLocations(locations);

        assertEquals(locations, userDTO.getLocations());
    }

    @Test
    void setLocations() {
        UserDTO userDTO = new UserDTO();
        List<LocationDTO> locations = new ArrayList<>();

        userDTO.setLocations(locations);

        assertEquals(locations, userDTO.getLocations());
    }
}