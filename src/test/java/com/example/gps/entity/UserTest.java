package com.example.gps.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getId() {
        Long id = 1L;
        User user = new User();
        user.setId(id);

        assertEquals(id, user.getId());
    }

    @Test
    void setId() {
        Long id = 1L;
        User user = new User();

        user.setId(id);

        assertEquals(id, user.getId());
    }

    @Test
    void getLogin() {
        String login = "testLogin";
        User user = new User();
        user.setLogin(login);

        assertEquals(login, user.getLogin());
    }

    @Test
    void setLogin() {
        String login = "testLogin";
        User user = new User();

        user.setLogin(login);

        assertEquals(login, user.getLogin());
    }

    @Test
    void getPassword() {
        String password = "testPassword";
        User user = new User();
        user.setPassword(password);

        assertEquals(password, user.getPassword());
    }

    @Test
    void setPassword() {
        String password = "testPassword";
        User user = new User();

        user.setPassword(password);

        assertEquals(password, user.getPassword());
    }

    @Test
    void getLocation() {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location());
        User user = new User();
        user.setLocation(locations);

        assertEquals(locations, user.getLocation());
    }

    @Test
    void setLocation() {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location());
        User user = new User();

        user.setLocation(locations);

        assertEquals(locations, user.getLocation());
    }
}
