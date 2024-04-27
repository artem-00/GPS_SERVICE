package com.example.gps.entity;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class HistoryTest {

    @Test
    void getId() {
        // Arrange
        Long id = 1L;
        History history = new History();
        history.setId(id);

        // Act & Assert
        assertEquals(id, history.getId());
    }

    @Test
    void setId() {
        // Arrange
        Long id = 1L;
        History history = new History();

        // Act
        history.setId(id);

        // Assert
        assertEquals(id, history.getId());
    }

    @Test
    void getIdUser() {
        // Arrange
        Long idUser = 1L;
        History history = new History();
        history.setIdUser(idUser);

        // Act & Assert
        assertEquals(idUser, history.getIdUser());
    }

    @Test
    void setIdUser() {
        // Arrange
        Long idUser = 1L;
        History history = new History();

        // Act
        history.setIdUser(idUser);

        // Assert
        assertEquals(idUser, history.getIdUser());
    }

    @Test
    void getIdLocation() {
        // Arrange
        Long idLocation = 1L;
        History history = new History();
        history.setIdLocation(idLocation);

        // Act & Assert
        assertEquals(idLocation, history.getIdLocation());
    }

    @Test
    void setIdLocation() {
        // Arrange
        Long idLocation = 1L;
        History history = new History();

        // Act
        history.setIdLocation(idLocation);

        // Assert
        assertEquals(idLocation, history.getIdLocation());
    }

    @Test
    void getIp() {
        // Arrange
        String ip = "192.168.1.1";
        History history = new History();
        history.setIp(ip);

        // Act & Assert
        assertEquals(ip, history.getIp());
    }

    @Test
    void setIp() {
        // Arrange
        String ip = "192.168.1.1";
        History history = new History();

        // Act
        history.setIp(ip);

        // Assert
        assertEquals(ip, history.getIp());
    }

    @Test
    void getRequestDateTime() {
        // Arrange
        Date requestDateTime = new Date();
        History history = new History();
        history.setRequestDateTime(requestDateTime);

        // Act & Assert
        assertEquals(requestDateTime, history.getRequestDateTime());
    }

    @Test
    void setRequestDateTime() {
        // Arrange
        Date requestDateTime = new Date();
        History history = new History();

        // Act
        history.setRequestDateTime(requestDateTime);

        // Assert
        assertEquals(requestDateTime, history.getRequestDateTime());
    }
}
